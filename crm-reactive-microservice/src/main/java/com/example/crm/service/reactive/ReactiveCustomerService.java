package com.example.crm.service.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentRepository;
import com.example.crm.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCustomerService implements CustomerService {

	@Autowired
	private CustomerDocumentRepository customerDocumentRepository;
	
	@Override
	public Mono<CustomerDocument> findCustomerByIdentity(String identity) {
		return customerDocumentRepository.findById(identity);
	}

	@Override
	public Flux<CustomerDocument> findCustomers(int page, int size) {
		return customerDocumentRepository.findAll(PageRequest.of(page, size));
	}

}
