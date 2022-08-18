package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.models.Customer;



public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	
	Optional<Customer> findBycontactdetail(String contactdetail);
}
