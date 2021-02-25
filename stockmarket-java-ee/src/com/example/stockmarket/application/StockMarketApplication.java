package com.example.stockmarket.application;

import java.util.List;

import javax.validation.Valid;

import com.example.stockmarket.entity.Stock;

public interface StockMarketApplication {

	Stock findStockBySymbol(String symbol);

	List<Stock> findStocks(int page, int size);

	Stock add(Stock stock);

	Stock update(@Valid Stock stock);

	Stock remove(@Valid String symbol);

}
