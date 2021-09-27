package br.inatel.quotationmanagement.controllers.form;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import br.inatel.quotationmanagement.repository.StockQuoteRepository;

public class StockQuoteFormTest {

    @Mock
    StockQuoteRepository stockQuoteRepositoryMock;

    @Mock
    StockQuoteForm stockQuoteFormMock;

    List<StockQuote> stockList;    

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        
        stockList = stockList();
    }
    
    @Test
    void hasToReturnAStockQuoteNotNull() {

        StockQuote stockQuote = stockList.get(0);

        Mockito.when(stockQuoteFormMock.convertAndMergeQuotes(stockQuoteRepositoryMock)).thenReturn(stockQuote);
        StockQuote stock = stockQuoteFormMock.convertAndMergeQuotes(stockQuoteRepositoryMock);

        Assert.notNull(stock, "Should be not null");
    }

    @Test
    void idHasToBeUUIDClass() {

        StockQuote stockQuote = stockList.get(1);

        Mockito.when(stockQuoteFormMock.convertAndMergeQuotes(stockQuoteRepositoryMock)).thenReturn(stockQuote);
        StockQuote stock = stockQuoteFormMock.convertAndMergeQuotes(stockQuoteRepositoryMock);
        
        Assert.isInstanceOf(UUID.class, stock.getId(), "Shoud be a UUID Class");
    }
    
    @Test
    void quotesHasToContain() {
        StockQuote stockQuote = stockList.get(1);
    
        Mockito.when(stockQuoteFormMock.convertAndMergeQuotes(stockQuoteRepositoryMock)).thenReturn(stockQuote);
        StockQuote stock = stockQuoteFormMock.convertAndMergeQuotes(stockQuoteRepositoryMock);
        
        Assert.isTrue(stock.getQuotes().containsKey("2022-09-27"), "Shold have this key: 2022-09-27");
        Assert.isTrue(stock.getQuotes().get("2022-09-27").compareTo(new BigDecimal("20")) >= 0, "Should be greather or equal than 20");
        Assert.isTrue(stock.getQuotes().get("2022-09-27").compareTo(new BigDecimal("150")) <= 0, "Should be lower or equal than 150");
    }

    @Test
    void stockIdHasToBeEqualThenVale5() {
        StockQuote stockQuote = stockList.get(2);
    
        Mockito.when(stockQuoteFormMock.convertAndMergeQuotes(stockQuoteRepositoryMock)).thenReturn(stockQuote);
        StockQuote stock = stockQuoteFormMock.convertAndMergeQuotes(stockQuoteRepositoryMock);

        Assert.hasText(stock.getStockId(), "Shold contain text");
        Assert.isTrue(stock.getStockId().contains("vale5"), "Should contains 'vale5'");
    }

    private List<StockQuote> stockList() {
        List<StockQuote> stockList = new ArrayList<>();

        stockList.add(new StockQuote(UUID.randomUUID(), "petr4", new HashMap<>()));
        stockList.add(new StockQuote(UUID.randomUUID(), "petr4", new HashMap<>()));
        stockList.add(new StockQuote(UUID.randomUUID(), "vale5", new HashMap<>()));

        stockList.stream().forEach(stock -> stock.getQuotes().put("2022-09-27", new BigDecimal((int) (Math.random() * (150 - 20)) + 20)));
        stockList.stream().forEach(stock -> stock.getQuotes().put("2022-09-28", new BigDecimal((int) (Math.random() * (150 - 20)) + 20)));
        stockList.stream().forEach(stock -> stock.getQuotes().put("2022-09-29", new BigDecimal((int) (Math.random() * (150 - 20)) + 20)));

        return stockList;
    }
}
