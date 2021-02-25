package com.example.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BinanceRestOverHttpClient {
	private static final String URL = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	public static void main(String[] args) throws IOException, InterruptedException {
		var httpClient = HttpClient.newHttpClient();
		var request = 
				HttpRequest.newBuilder()
		           .uri(URI.create(URL))
		           .header("Accept", "application/json")
		           .build();
		var start = System.currentTimeMillis();
		for (var i=0;i<10;++i) {
			var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
			System.out.println(response);			
		}
		var stop = System.currentTimeMillis();
		System.out.println("Duration: "+(stop-start)+" ms.");
	}

}
