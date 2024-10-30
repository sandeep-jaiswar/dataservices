package com.sandeepsecurities.dataservices.services;

import com.sandeepsecurities.dataservices.models.Stock;

public interface StockService {
    Stock get(String symbol);
    Stock create(Stock stock);
    Stock update(String symbol, Stock stockDetails);
    void delete(String symbol);
}
