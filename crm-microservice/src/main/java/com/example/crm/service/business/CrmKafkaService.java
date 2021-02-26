package com.example.crm.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.service.events.CustomerAcquiredEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CrmKafkaService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private ObjectMapper mapper;
	@EventListener
	public void listenEvent(CustomerAcquiredEvent event) {
		try {
			kafkaTemplate.send("customers", mapper.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			System.err.println(e.getMessage());
		}
	}
}
