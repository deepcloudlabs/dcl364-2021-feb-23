package com.example.stockmarket.dto;

public class RestErrorMessage {
	private String reason;

	public RestErrorMessage(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}
	
}
