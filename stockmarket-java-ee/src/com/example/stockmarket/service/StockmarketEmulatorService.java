package com.example.stockmarket.service;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.stockmarket.application.StockMarketApplication;
import com.example.stockmarket.entity.Stock;

@Stateless
public class StockmarketEmulatorService {
	@Inject
	private StockMarketApplication stockMarketApp;

	@Schedule(second = "*/5", hour = "*", minute = "*", persistent = false)
	public void updateStockPricesRandomly() {
		stockMarketApp.findStocks(0, 10).forEach(stock -> {
			var updatedStock = new Stock(stock);
			updatedStock.setPrice(getRandomPrice(stock.getPrice()));
			stockMarketApp.update(updatedStock);
		});
	}

	private static double getRandomPrice(double oldPrice) {
		return oldPrice * (1.0 + 0.01 * (ThreadLocalRandom.current().nextDouble(1) - 0.5));
	}
}
