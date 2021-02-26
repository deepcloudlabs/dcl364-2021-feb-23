package com.example.crm.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.crm.document.CustomerDocument;
import com.example.crm.entity.Customer;

public interface CustomerDocumentRepository extends MongoRepository<CustomerDocument, String>{
	public List<CustomerDocument> findAllByBirthYearBetween(int fromYear,int toYear);

	@Query("{ $and: {$gt: ?0, $lt: ?1} }")
	public List<Customer> yasaGoreGetir(int fromYear,int toYear);	
}
