package com.example.domain;

//Pet is an interface -> ✘ new -> ✘ instantiate object
public abstract interface Pet {
	public static final int x= 42;
	int y = 42; // implicit: public static final
	void play(); // implicit: abstract public 
	abstract String getName(); // implicit: abstract public 
	public void setName(String name); // implicit: abstract public 
}
