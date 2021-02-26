package com.example.crm.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crm.document.CustomerDocument;

public interface CustomerDocumentRepository extends MongoRepository<CustomerDocument, String>{
	public List<CustomerDocument> findAllByBirthYearBetween(int fromYear,int toYear);
}
