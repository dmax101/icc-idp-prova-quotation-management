package br.inatel.quotationmanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.quotationmanagement.model.StockQuote;

public interface StockQuoteRepository extends JpaRepository<StockQuote, UUID> {
    
}
