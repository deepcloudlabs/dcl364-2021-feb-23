package com.example.crm.service;

import java.util.List;

import com.example.crm.entity.Customer;

public interface CustomerService {

	Customer findById(String identity);

	List<Customer> findByPage(int page, int size);

	Customer create(Customer customer);

	Customer update(Customer customer);

	Customer removeById(String identity);

}
