package com.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.config.AppConstants;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.models.Customer;
import com.ecommerce.models.Role;
import com.ecommerce.payload.CustomerDto;
import com.ecommerce.repository.CustomerRepo;
import com.ecommerce.repository.RoleRepo;
import com.ecommerce.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private RoleRepo roleRepo;
	

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		
		Customer customer = this.dtoToCustomer(customerDto);
		Customer savedCustomer = this.customerRepo.save(customer);
		return this.customerToDto(savedCustomer);
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto, Integer customerId) {
		
		Customer customer = this.customerRepo.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer","Id",customerId));
		
		customer.setName(customerDto.getName());
		customer.setContactdetail(customerDto.getContactdetail());
		customer.setPassword(customerDto.getPassword());
		
		
		Customer updatedCustomer = this.customerRepo.save(customer);
		CustomerDto customerDto1 = this.customerToDto(updatedCustomer);
		
		return customerDto1;
	}

	@Override
	public CustomerDto getCustomerById(Integer customerId) {
		
		Customer customer = this.customerRepo.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException("Customer","Id",customerId));
		return this.customerToDto(customer);
	}

	@Override
	public List<CustomerDto> getAllCustomers() {
		
		List<Customer> customers  = this.customerRepo.findAll();
	    List<CustomerDto> customerDtos = customers.stream()
			.map(customer -> this.customerToDto(customer)).collect(Collectors.toList());
	    
	    return customerDtos;
		
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		
		Customer customer = this.customerRepo.findById(customerId)
		.orElseThrow(()-> new ResourceNotFoundException("Customer","Id",customerId));
		
		this.customerRepo.delete(customer);

	}
	
	private Customer dtoToCustomer(CustomerDto customerDto) {
		
		Customer customer = this.modelMapper.map(customerDto, Customer.class);
//		customer.setId(customerDto.getId());
//		customer.setName(customerDto.getName());
//		customer.setContact_detail(customerDto.getContact_detail());
//		customer.setPassword(customerDto.getPassword());
		
	
		
		return customer;
	}
	
	public CustomerDto customerToDto(Customer customer) {
		
		CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class); 	
		return customerDto;
	}

	@Override
	public CustomerDto registerNewCustomer(CustomerDto customerDto) {
		Customer customer = this.modelMapper.map(customerDto, Customer.class);
		
		//encoded the password
		customer.setPassword(this.passwordEncoder.encode(customer.getPassword()));
		
		//roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		customer.getRoles().add(role);
		
		Customer newCustomer = this.customerRepo.save(customer);

		return this.modelMapper.map(newCustomer, CustomerDto.class);
	}

}