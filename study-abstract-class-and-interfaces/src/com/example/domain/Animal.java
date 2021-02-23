package com.example.domain;

// Alt+Shift+S
// Animal is an abstract class -> ✘ new -> ✘ instantiate object
public abstract class Animal {
	private int legs; // attribute

	public Animal(int legs) { // constructor
		this.legs = legs;
	}

	public int getLegs() { // concrete method
		return legs;
	}

	public abstract void eat(); // abstract method

	public abstract void walk(); // abstract method
}
