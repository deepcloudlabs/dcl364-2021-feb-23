package com.example.crm.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaCrmConsumer {

	@KafkaListener(groupId = "crm",topics = "customers")
	public void listen(String message) {
		System.err.println("Kafka: "+message);
	}
	
}
