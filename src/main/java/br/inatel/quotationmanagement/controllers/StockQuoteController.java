package br.inatel.quotationmanagement.controllers;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.quotationmanagement.controllers.form.StockQuoteForm;
import br.inatel.quotationmanagement.model.StockQuote;
import br.inatel.quotationmanagement.model.error.ErrorModel;
import br.inatel.quotationmanagement.repository.StockQuoteRepository;
import br.inatel.quotationmanagement.services.StockManager;

@RestController
@RequestMapping("/stock")
public class StockQuoteController {

    @Autowired
    private StockQuoteRepository stockQuoteRepository;

    @Autowired
    private StockManager stockManager;
    
    // Create a Stock Quote
    @PostMapping
    @CacheEvict(value="stockQuoteList", allEntries=true)
    public ResponseEntity<?> create(@RequestBody StockQuoteForm quoteForm, UriComponentsBuilder uriBuilder) {

        StockQuote stockQuote = quoteForm.convertAndMergeQuotes(stockQuoteRepository);

        if (!stockManager.isValidStock(stockQuote)) {
            return ((BodyBuilder) ResponseEntity.notFound()).body(
                new ErrorModel(
                    "stockId",
                    "There's no " + stockQuote.getStockId() + " assossiated with Stock Manager",
                    HttpStatus.NOT_FOUND.toString()
            ));
        }

        stockQuoteRepository.save(stockQuote);
        
        URI uri = uriBuilder.path("/stocks/{id}").buildAndExpand(stockQuote.getId()).toUri();


        return ResponseEntity.created(uri).body(stockQuote);
    }

    @GetMapping
    @Cacheable(value="stockQuoteList")
    public ResponseEntity<List<StockQuote>> list(String id, String stockId) {

        if (id == null && stockId == null) {
            
            List<StockQuote> stockQuotes = stockQuoteRepository.findAll();

            if (stockQuotes.isEmpty()) {

                return ResponseEntity.notFound().build();
            }
            
            return ResponseEntity.ok().body(stockQuotes);
            
        } else if (stockId == null) {
            
            Optional<StockQuote> stockQuote = stockQuoteRepository.findById(UUID.fromString(id));

            if (stockQuote.isPresent()) {

                return ResponseEntity.ok(Arrays.asList(stockQuote.get()));
            } else {

                return ResponseEntity.notFound().build();
            }
        } else if (id == null) {

            List<StockQuote> stocksQuotes = stockQuoteRepository.findByStockId(stockId);

            if (stocksQuotes.isEmpty()) {

                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(stockQuoteRepository.findByStockId(stockId));

        } else {

            List<StockQuote> stockQuote = stockQuoteRepository.findByIdAndStockId(UUID.fromString(id), stockId);

            if (stockQuote.isEmpty()) {

                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(stockQuote);
        }
    }
}
