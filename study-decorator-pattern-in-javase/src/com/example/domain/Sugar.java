package com.example.domain;

// Decorator
public class Sugar implements Product {
	private Product product;
	
	public Sugar(Product product) {
		this.product = product;
	}

	@Override
	public double getPrice() {
		return product.getPrice() + 0.5 ;
	}

}
