package com.sandeepsecurities.dataservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandeepsecurities.dataservices.models.Stock;
import com.sandeepsecurities.dataservices.services.StockService;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam String symbol) {
        try {
            var stockData = stockService.get(symbol);
            return ResponseEntity.ok(stockData);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching stock data: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Stock stock) {
        try {
            Stock createdStock = stockService.create(stock);
            return ResponseEntity.status(201).body(createdStock);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating stock: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam String symbol, @RequestBody Stock stockDetails) {
        try {
            Stock updatedStock = stockService.update(symbol, stockDetails);
            return ResponseEntity.ok(updatedStock);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating stock: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam String symbol) {
        try {
            stockService.delete(symbol);
            return ResponseEntity.ok("Stock deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting stock: " + e.getMessage());
        }
    }
}
