package com.sandeepsecurities.dataservices.services.impl;

import com.sandeepsecurities.dataservices.models.Stock;
import com.sandeepsecurities.dataservices.repositories.StockRepository;
import com.sandeepsecurities.dataservices.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock get(String symbol) {
        return stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Stock data not found for symbol: " + symbol));
    }

    @Override
    public Stock create(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock update(String symbol, Stock stockDetails) {
        Stock existingStock = stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Stock not found with symbol: " + symbol));

        existingStock.setData(stockDetails.getData());
        return stockRepository.save(existingStock);
    }

    @Override
    public void delete(String symbol) {
        Stock stock = stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Stock not found with symbol: " + symbol));

        stockRepository.delete(stock);
    }
}
