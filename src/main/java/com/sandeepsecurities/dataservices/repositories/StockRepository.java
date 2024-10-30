package com.sandeepsecurities.dataservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sandeepsecurities.dataservices.models.Stock;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findBySymbol(String symbol);
}
