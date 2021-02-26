package com.example.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CrmRestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("{identity}")
	Mono<CustomerDocument> findById(@PathVariable String identity){
		return customerService.findCustomerByIdentity(identity);
	} 
	
	@GetMapping()
	Flux<CustomerDocument> findAll(@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "25") int size){
		return customerService.findCustomers(page,size);
	} 
}
