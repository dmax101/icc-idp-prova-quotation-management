package br.inatel.quotationmanagement.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import br.inatel.quotationmanagement.model.StockQuote;

public class StockManagerTest {

    @Mock
    StockManager stockManager;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void hasToReceiveEmptyList() {
        List<String> stockList = stockManager.getStocksFromStockManager();

        Assert.isTrue(stockList.isEmpty(), "Shold be empty");
    }

    @Test
    void hasToReceiveNotEmptyList() {
        List<String> list = Arrays.asList("petr4", "vale5");
        Mockito.when(stockManager.getStocksFromStockManager()).thenReturn(list);
        List<String> stockList = stockManager.getStocksFromStockManager();

        Assert.notEmpty(stockList, "Shold be filled");
    }

    @Test
    void hasToBeAValidStock() {
        
        StockQuote stockQuote = new StockQuote(
            UUID.randomUUID(),
            "petr4",
            new HashMap<>());

        Mockito.when(stockManager.isValidStock(stockQuote)).thenReturn(true);

        Assert.isTrue(stockManager.isValidStock(stockQuote), "Shold be a valid stock");
    }
    
}
