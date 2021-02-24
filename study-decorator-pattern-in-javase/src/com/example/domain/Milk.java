package com.example.domain;

// Decorator
public class Milk implements Product {
	private Product product;
	
	public Milk(Product product) {
		this.product = product;
	}

	@Override
	public double getPrice() {
		return product.getPrice() + 2.5 ;
	}

}
