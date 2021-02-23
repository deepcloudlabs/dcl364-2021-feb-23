package com.example.domain;

// Spider is a concrete class -> new -> instantiate object
public class Spider extends Animal {

	public Spider() {
		super(8);
	}

	@Override
	public void eat() {
		System.err.println("Spider is eating now...");
	}

	@Override
	public void walk() {
		System.err.println("Spider is walking now...");
	}

}
