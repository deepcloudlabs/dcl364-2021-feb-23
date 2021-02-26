package com.example.crm.document;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="customers")
public class CustomerDocument {
	@Id
	private String identity;
	@Field(name = "full_name")
	private String fullName;
	@Field(name = "birth_year")
	private int birthYear;
	@Email
	private String email;
	private String sms;
	private String address;

	public CustomerDocument() {
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [identity=" + identity + ", fullName=" + fullName + ", birthYear=" + birthYear + ", email="
				+ email + ", sms=" + sms + ", address=" + address + "]";
	}

}
