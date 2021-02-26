package com.example.crm.service;

import com.example.crm.document.CustomerDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

	Mono<CustomerDocument> findCustomerByIdentity(String identity);

	Flux<CustomerDocument> findCustomers(int page, int size);

}
