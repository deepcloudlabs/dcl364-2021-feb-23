package com.example.crm.service;

import java.lang.reflect.Type;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.example.crm.service.events.CustomerAcquiredEvent;

@Service
public class WebsocketCrmConsumer implements StompSessionHandler {

	@Autowired
	private WebSocketStompClient webSocketStompClient;
	
	@PostConstruct
	public void init() {
		webSocketStompClient.connect("ws://localhost:5500/crm/api/v1/changes", this);
	}
	
	@Override
	public Type getPayloadType(StompHeaders headers) {
		return CustomerAcquiredEvent.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		System.err.println("Websocket: "+payload);
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		System.err.println("Websocket connection is closed!");
		session.subscribe("/topic/changes", this);
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		System.err.println("Error in session: "+exception.getMessage());
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		System.err.println("Error in session: "+exception.getMessage());
		
	}
	
}
