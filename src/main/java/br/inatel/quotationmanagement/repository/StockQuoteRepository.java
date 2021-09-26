package br.inatel.quotationmanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.quotationmanagement.model.StockQuote;

public interface StockQuoteRepository extends JpaRepository<StockQuote, UUID> {

    List<StockQuote> findByStockId(String stockId);

    List<StockQuote> findByIdAndStockId(UUID id, String stockId);
    
}
