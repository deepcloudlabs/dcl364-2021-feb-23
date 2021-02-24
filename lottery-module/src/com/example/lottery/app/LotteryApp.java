package com.example.lottery.app;

import java.util.ServiceLoader;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.business.SimpleLotteryService;
import com.example.random.service.RandomNumberService;
//import com.example.random.service.business.StandardRandomNumberService;

public class LotteryApp {

	public static void main(String[] args) {
		ServiceLoader<RandomNumberService> randomNumberServices = 
				ServiceLoader.load(RandomNumberService.class);
		var randomNumberService = randomNumberServices.findFirst().get();
		LotteryService lotteryService = 
				new SimpleLotteryService(randomNumberService );
		System.out.println(lotteryService.draw(60, 6));
		System.err.println(randomNumberService.getClass().getName());
	}

}
