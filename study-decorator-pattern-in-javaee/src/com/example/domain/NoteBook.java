package com.example.domain;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class NoteBook implements Product {

	@Override
	public double getPrice() {
		return 16_000;
	}

}
