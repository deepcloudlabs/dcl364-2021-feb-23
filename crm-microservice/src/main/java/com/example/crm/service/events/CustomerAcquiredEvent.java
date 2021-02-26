package com.example.crm.service.events;

public class CustomerAcquiredEvent {
	private String identity;
	private String email;

	public CustomerAcquiredEvent() {
	}

	public CustomerAcquiredEvent(String identity, String email) {
		this.identity = identity;
		this.email = email;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CustomerAcquiredEvent [identity=" + identity + ", email=" + email + "]";
	}

}
