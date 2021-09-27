package br.inatel.quotationmanagement.controllers;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stockcache")
public class CacheStockQuoteController {
    
    @DeleteMapping
    @CacheEvict(value="stockQuoteList", allEntries=true)
    public ResponseEntity<?> deleteCache() {
        return ResponseEntity.ok().build();
    }

}
