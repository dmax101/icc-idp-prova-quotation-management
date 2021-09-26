package br.inatel.quotationmanagement.controllers.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import br.inatel.quotationmanagement.model.StockQuote;
import br.inatel.quotationmanagement.repository.StockQuoteRepository;
public class StockQuoteForm {

    private UUID id;
    private String stockId;
    private Map<String, BigDecimal> quotes;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStockId() {
        return this.stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Map<String,BigDecimal> getQuotes() {
        return this.quotes;
    }

    public void setQuotes(Map<String,BigDecimal> quotes) {
        this.quotes = quotes;
    }

    public StockQuote convertAndMergeQuotes(StockQuoteRepository stockQuoteRepository) {

        Optional<StockQuote> stockQuoteFromRepository = stockQuoteRepository.findById(this.id);

        if (stockQuoteFromRepository.isPresent() && stockQuoteFromRepository.get().getStockId().equals(this.stockId)) {
            StockQuote stockQuote = stockQuoteFromRepository.get();

            Map<String, BigDecimal> dbQuotes = new HashMap<>(stockQuote.getQuotes());
            Map<String, BigDecimal> apiQuotes = new HashMap<>(this.quotes);
            dbQuotes.forEach((key, value) -> apiQuotes.merge(key, value, (v1, v2) -> v1));
            stockQuote.setQuotes(apiQuotes);
            
            return stockQuote;
        } else if (stockQuoteFromRepository.isPresent() && !stockQuoteFromRepository.get().getStockId().equals(this.stockId)) {

            return new StockQuote(this.stockId, this.quotes);
        } else {
            
            return new StockQuote(this.id, this.stockId, this.quotes);
        }
    }


}
