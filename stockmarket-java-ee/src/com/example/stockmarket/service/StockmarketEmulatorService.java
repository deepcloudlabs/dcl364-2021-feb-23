package com.example.stockmarket.service;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.stockmarket.application.StockMarketApplication;

@Stateless
public class StockmarketEmulatorService {
	@Inject
	private StockMarketApplication stockMarketApp;
	
	@Schedule(second="*/5", hour="*", minute = "*", persistent = false)
	public void updateStockPricesRandomly() {
		stockMarketApp.findStocks(0, 10)
		              .forEach( stock -> {
						 stock.setPrice( getRandomPrice(stock.getPrice()) );
						 stockMarketApp.update(stock);
		              });  
	}

	private static double getRandomPrice(double oldPrice) {
		return oldPrice * ( 1.0 + ThreadLocalRandom.current().nextDouble(-0.05,0.05) );
	}
}
