package com.example.stockmarket.application.business;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;

import com.example.stockmarket.application.StockMarketApplication;
import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.repository.StockRepository;

@Stateless
public class StandardStockMarketApplication implements StockMarketApplication {

	@Inject
	private StockRepository stockRepository;
	
	@Override
	public Stock findStockBySymbol(String symbol) {
		return stockRepository.findOne(symbol)
				              .orElseThrow(() -> new IllegalArgumentException("Cannot find stock."));
	}

	@Override
	public List<Stock> findStocks(int page, int size) {
		return stockRepository.findAll(page, size);
	}

	@Override
	public Stock add(Stock stock) {
		try {
			return stockRepository.create(stock).get();
		} catch (InterruptedException | ExecutionException e) {
			throw new IllegalStateException(e.getMessage());
		}
	}

	@Override
	public Stock update(@Valid Stock stock) {
		return stockRepository.update(stock);
	}

	@Override
	public Stock remove(@Valid String symbol) {
		return stockRepository.removeById(symbol);
	}

}
