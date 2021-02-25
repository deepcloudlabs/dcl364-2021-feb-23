package com.example.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BinanceRestOverHttpAsyncClient {
	private static final String URL = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	private static final AtomicInteger COUNTER = new AtomicInteger();
	public static void main(String[] args) throws InterruptedException {
		var httpClient = HttpClient.newHttpClient();
		var request = 
				HttpRequest.newBuilder()
		           .uri(URI.create(URL))
		           .header("Accept", "application/json")
		           .build();
		var start = System.currentTimeMillis();
		for (var i=0;i<10;++i) {
			httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			          .thenAccept( ticker -> {
			        	  var counter = COUNTER.incrementAndGet();
			        	  if (counter==10) {
			        		  var stop = System.currentTimeMillis();
			        		  System.err.println("Duration: "+(stop-start)+" ms.");
			        	  }
			        	  System.err.println(ticker.body());
			          });
		}
		System.err.println("All requests are sent.");
		TimeUnit.SECONDS.sleep(5);
	}

}
