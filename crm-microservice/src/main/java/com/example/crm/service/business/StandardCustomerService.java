package com.example.crm.service.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crm.entity.Customer;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.service.CustomerService;
import com.example.crm.service.events.CustomerAcquiredEvent;

@Service
public class StandardCustomerService implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	public Customer findById(String identity) {
		var customer = customerRepository.findById(identity);
		if (customer.isEmpty())
			throw new IllegalArgumentException("Cannot find customer");
		return customer.get();
	}

	@Override
	public List<Customer> findByPage(int page, int size) {
		return customerRepository.findAll(PageRequest.of(page, size))
				                 .getContent();
	}

	@Override
	@Transactional
	public Customer create(Customer customer) {
		var identity = customer.getIdentity();
		if (customerRepository.existsById(identity ))
			throw new IllegalArgumentException("Customer already exists.");
		var event = new CustomerAcquiredEvent(identity,customer.getEmail());
		publisher.publishEvent(event);
		return customerRepository.save(customer);
	}

	@Override
	@Transactional
	public Customer update(Customer customer) {
		var identity = customer.getIdentity();
		var optionalManagedCustomer = customerRepository.findById(identity);
		if (optionalManagedCustomer.isEmpty())
			throw new IllegalArgumentException("Cannot find customer to update.");
		var managedCustomer = optionalManagedCustomer.get();
		managedCustomer.setSms(customer.getSms());
		managedCustomer.setEmail(customer.getEmail());
		managedCustomer.setAddress(customer.getAddress());
		return managedCustomer;
	}

	@Override
	@Transactional
	public Customer removeById(String identity) {
		var optionalManagedCustomer = customerRepository.findById(identity);
		if (optionalManagedCustomer.isEmpty())
			throw new IllegalArgumentException("Cannot find customer to remove.");
		var managedCustomer = optionalManagedCustomer.get();
		customerRepository.deleteById(identity);
		return managedCustomer;
	}

}
