package com.example.crm.service.business;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.crm.service.events.CustomerAcquiredEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CrmRabbitService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
	@Autowired
	private ObjectMapper mapper;
	@EventListener
	public void listenEvent(CustomerAcquiredEvent event) {
		try {
			rabbitTemplate.convertAndSend("crm", null, mapper.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			System.err.println(e.getMessage());
		}
	}
}
