package br.inatel.quotationmanagement.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.inatel.quotationmanagement.model.StockQuote;

@Service
public class StockManager {

    @Autowired
    RestTemplate restTemplate;
    
    @Cacheable(value="stockQuoteConsultation")
    public List<String> getStocksFromStockManager() {
        List<?> stockManagerList = restTemplate.getForObject("http://localhost:8080/stock", List.class);

        if (stockManagerList == null) {
            return List.of();
        }

        return stockManagerList.stream()
            .map(LinkedHashMap.class::cast)
            .map(stock -> (String) stock.get("id"))
            .collect(Collectors.toList());        
    }

    public boolean isValidStock(StockQuote stockQuote) {

        return getStocksFromStockManager().contains(stockQuote.getStockId());
    }
}
