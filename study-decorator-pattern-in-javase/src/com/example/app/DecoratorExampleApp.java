package com.example.app;

import com.example.domain.*;

public class DecoratorExampleApp {

	public static void main(String[] args) {
		var product1 = new Sugar(new Sugar(new TurkishCoffee()));
		var product2 = new Sugar(new Sugar(new Tea()));
		System.err.println("Total price for product 1: "+product1.getPrice());
		System.err.println("Total price for product 2: "+product2.getPrice());
	}

}
