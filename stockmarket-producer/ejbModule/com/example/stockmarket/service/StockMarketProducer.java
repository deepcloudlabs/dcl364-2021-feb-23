package com.example.stockmarket.service;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.Json;

import com.example.stockmarket.entity.Stock;

@Stateless
public class StockMarketProducer {
    // JMS 2.0
	@Inject
	private JMSContext jmsContext;
	@Resource(mappedName = "java:/jms/queue/stockQueue") // JNDI Name
    private Queue queue;
	
	@Schedule(second="*/5",hour = "*", minute = "*")
	public void sendStockPriceChangeEvent() {
		var price = ThreadLocalRandom.current().nextDouble(90, 110);
		Stock stock = new Stock("ORCL", "Oracle", "Oracle Inc.", price );
		jmsContext.createProducer().send(queue, createJson(stock) );
	}

	private String createJson(Stock stock) {
		return Json.createObjectBuilder()
					.add("symbol", stock.getSymbol())
					.add("price", stock.getPrice())
					.build()
					.toString();
	}
}
