package com.example.domain;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class Discount implements Product {

	@Delegate
	@Inject
	@Any
	private Product product;
	
	@Override
	public double getPrice() {
		return 0.80 * product.getPrice();
	}

}
