package com.example.crm.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitCrmConsumer {

	@RabbitListener(queues = "customers")
	public void listen(String message) {
		System.err.println("Rabbit: "+message);
	}
	
}
