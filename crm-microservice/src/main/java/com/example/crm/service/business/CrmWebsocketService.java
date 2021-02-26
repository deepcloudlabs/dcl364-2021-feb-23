package com.example.crm.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.service.events.CustomerAcquiredEvent;

@Service
public class CrmWebsocketService {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@EventListener
	public void listenEvent(CustomerAcquiredEvent event) {
		messagingTemplate.convertAndSend("/topic/changes", event);
	}
}
