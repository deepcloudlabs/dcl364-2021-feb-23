package com.example.domain;

public class Cat extends Animal implements Pet {
	private String name;

	public Cat(String name) {
		super(4);
		this.name = name;
	}

	@Override
	public void play() {
		System.out.println(name + " is playing now...");
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void eat() {
		System.out.println(name + " is eating now...");
	}

	@Override
	public void walk() {
		System.out.println(name + " is walking now...");
	}
}
