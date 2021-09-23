package br.inatel.quotationmanagement.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class StockQuote {

    @Id
    @Type(type="uuid-char")
    private UUID id;
    
    private String stockId;
    
    @ElementCollection
    private Map<String, BigDecimal> quotes;

    public StockQuote() {
    }

    public StockQuote(String stockId, Map<String, BigDecimal> quotes) {
        this.id = UUID.randomUUID();
        this.stockId = stockId;
        this.quotes = quotes;
    }

    public StockQuote(UUID id, String stockId, Map<String,BigDecimal> quotes) {
        this.id = id;
        this.stockId = stockId;
        this.quotes = quotes;
    }

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
    
}
