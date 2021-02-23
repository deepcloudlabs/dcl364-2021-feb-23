package com.example.app;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.example.domain.Animal;
import com.example.domain.Spider;
import com.example.domain.Cat;
import com.example.domain.Pet;

public class AnimalApp {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<Animal> animals = List.of( new Spider(), new Cat("Tekir"), new Spider(),
				                        new Cat("Garfield"));
		// external loop
		for (var animal : animals) { // iterator pattern
			animal.walk();
			animal.eat();
			if (animal instanceof Pet) { // guard
				((Pet)animal).play(); // safe
			}
		}
		// Since Java SE 8: Functional Programming
		
		Consumer<Animal> walk1 = new Consumer<Animal>() { // anonymous class
			@Override
			public void accept(Animal t) {
				t.walk();
			}
		};
		Consumer<Animal> walk2 = (Animal t) -> {
				t.walk();
		};
		Consumer<Animal> walk3 = (t) -> {
			t.walk();
		};
		Consumer<Animal> walk4 = t -> {
			t.walk();
		};
		Consumer<Animal> walk5 = animal -> animal.walk(); // Lambda Expression (Java SE 8)
		Consumer<Animal> walk = Animal::walk; // Method Reference  (Java SE 8)
		Consumer<Animal> eat = Animal::eat;  // animal -> animal.eat();
		Consumer<Animal> playIfPet = animal -> {
			if (animal instanceof Pet) { 
				((Pet)animal).play(); 
			}
		};
		// internal loop
		animals.forEach(walk.andThen(eat).andThen(playIfPet));
		
		// Lottery: 6, [1..60], sorted
		var lotteryNumbers = 
		ThreadLocalRandom.current().ints(1,61) // Stream API -> Collection API
		                           .parallel() // 8 Core -> 32 Core -> 80 Core
		                           .distinct()
		                           .limit(6)
		                           .sorted()
		                           .boxed()
		                           .collect(Collectors.toList());
		System.out.println(lotteryNumbers);
		List<Integer> numbers = List.of(4,8,15,16,23,42);
		var sum= numbers.stream().filter(Number::isEven)
		                         .map(Number::squared)
		                         .reduce(0, Number::sum);
		System.out.println("Sum: "+sum);
	}
}
interface Number {
	public static boolean isOdd(Integer n) {
		return n%2 == 1;
	}
	public static boolean isEven(Integer n) {
		return n%2 == 0;
	}
	public static int squared(Integer n) {
		return n*n;
	}
	public static int sum(Integer acc,Integer n) {
		return acc+n;
	}
}
@SuppressWarnings("unused")
@FunctionalInterface // (1) Java SE 8 
interface Fun {
	public int haveFun();
	public default int haveSun() { // default method (2)
		return 42;
	}
	public static int haveGun() { // static method (3) -> Utility Methods for FP
		return 108;
	}
	private static int haveRun() { // Java SE 9
		return 549;
	}
	private int haveBun() { // Java SE 9
		return 549;
	}
}