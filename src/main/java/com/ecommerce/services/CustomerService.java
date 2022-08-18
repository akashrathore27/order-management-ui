package com.ecommerce.services;

import java.util.List;

import com.ecommerce.payload.CustomerDto;


public interface CustomerService {
	
	CustomerDto registerNewCustomer(CustomerDto customer);
	
	CustomerDto createCustomer(CustomerDto customer);
	
	CustomerDto updateCustomer(CustomerDto customer, Integer customerId);
	CustomerDto getCustomerById(Integer customerId);
	
	List<CustomerDto> getAllCustomers();
	
	void deleteCustomer(Integer customerId);

}