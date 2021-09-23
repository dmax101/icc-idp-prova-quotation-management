package br.inatel.quotationmanagement.controllers;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.quotationmanagement.controllers.form.StockQuoteForm;
import br.inatel.quotationmanagement.model.StockQuote;
import br.inatel.quotationmanagement.repository.StockQuoteRepository;

@RestController
@RequestMapping("/stock")
public class StockQuoteController {

    @Autowired
    private StockQuoteRepository stockQuoteRepository;
    
    // Create a Stock Quote
    @PostMapping
    public ResponseEntity<StockQuote> create(@RequestBody StockQuoteForm quoteForm, UriComponentsBuilder uriBuilder) {
        
        StockQuote stockQuote = quoteForm.convertAndMergeQuotes(stockQuoteRepository);

        stockQuoteRepository.save(stockQuote);
        
        URI uri = uriBuilder.path("/stocks/{id}").buildAndExpand(stockQuote.getId()).toUri();

        return ResponseEntity.created(uri).body(stockQuote);
    }

    @GetMapping
    public List<StockQuote> list(String id) {

        if (id == null) {
            return stockQuoteRepository.findAll();    
        } else {
            Optional<StockQuote> stockQuote = stockQuoteRepository.findById(UUID.fromString(id));

            if (stockQuote.isPresent()) {
                return Arrays.asList(stockQuote.get());
            } else {
                return Arrays.asList((StockQuote) null);
            }
        }
    }
}
