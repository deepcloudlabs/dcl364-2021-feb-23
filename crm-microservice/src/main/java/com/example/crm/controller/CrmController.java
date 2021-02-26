package com.example.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.crm.entity.Customer;
import com.example.crm.service.CustomerService;

@RestController
@RequestScope
@RequestMapping("customers")
@CrossOrigin("*")
public class CrmController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(value="{identity}")
	public Customer getCustomerByIdentity(@PathVariable String identity) {
		return customerService.findById(identity);
	}
	
	@GetMapping
	public List<Customer> getCustomersByPage(@RequestParam int page,@RequestParam int size) {
		return customerService.findByPage(page,size);
	}
	
	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.create(customer);
	}
	
	@PutMapping
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerService.update(customer);
	}
	
	@DeleteMapping(value="{identity}")
	public Customer removeCustomerByIdentity(@PathVariable String identity) {
		return customerService.removeById(identity);
	}
}
