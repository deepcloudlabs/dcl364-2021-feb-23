package com.example.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.crm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{
	public List<Customer> findAllByBirthYearBetween(int fromYear,int toYear);
	
	@Query("select c from Customer c where c.birthYear between :fromYear and :toYear")
	public List<Customer> yasaGoreGetir(int fromYear,int toYear);
}
