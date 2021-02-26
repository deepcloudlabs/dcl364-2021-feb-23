package com.example.stockmarket.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: StockmarketMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/stockQueue"), 
				@ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		})
public class StockmarketMDB implements MessageListener {

    public void onMessage(Message message) {
       if (message instanceof TextMessage) {
    	   try {
			var json = ((TextMessage)message).getText();
			System.out.println(json);
		} catch (JMSException e) {
			System.err.println("Error: "+e.getMessage());
		}
       } 
    }

}
