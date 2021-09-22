package br.inatel.quotationmanagement.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.inatel.quotationmanagement.model.StockQuote;
import br.inatel.quotationmanagement.repository.StockQuoteRepository;

@Controller
public class StockQuoteController {

    @Autowired
    private StockQuoteRepository stockQuoteRepository;
    
    @RequestMapping("/list")
    @ResponseBody
    public List<StockQuote> createStockQuote() {

        Map<String,BigDecimal> quotes = new HashMap<>();

        quotes.put("2019-01-01", new BigDecimal("10"));
        quotes.put("2019-01-02", new BigDecimal("11"));
        quotes.put("2019-01-03", new BigDecimal("14"));

        StockQuote stockQuote01 = new StockQuote("mglu3", quotes);
        StockQuote stockQuote02 = new StockQuote("petr3", quotes);
        StockQuote stockQuote03 = new StockQuote("bahi3", quotes);

        stockQuoteRepository.save(stockQuote01);
        stockQuoteRepository.save(stockQuote02);
        stockQuoteRepository.save(stockQuote03);
        
        return Arrays.asList(stockQuote01, stockQuote02, stockQuote03);
    }
}
