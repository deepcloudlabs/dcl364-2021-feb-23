package com.example.stockmarket.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.json.Json;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.example.stockmarket.application.events.StockPriceChangedEvent;

@Singleton
@ServerEndpoint("/changes")
public class StockMarketWebSocketService {
	private Map<String,Session> sessions = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpenSession(Session session) {
		sessions.put(session.getId(), session);
	}
	
	@OnClose
	public void onCloseSession(Session session) {
		sessions.remove(session.getId());
	}
	
	public void listenStockPriceChanges(@Observes StockPriceChangedEvent event) {
		var json = Json.createObjectBuilder()
		    .add("symbol", event.getSymbol())
		    .add("oldPrice", event.getOldPrice())
		    .add("newPrice", event.getNewPrice())
		    .build()
		    .toString();
		sessions.forEach((sessionId, session) -> {
			try {
				session.getBasicRemote().sendText(json);
			} catch (IOException e) {
				System.err.println("Error: "+e.getMessage());
			}
		});
	}
}
