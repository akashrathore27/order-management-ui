package com.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.payload.ApiResponse;
import com.ecommerce.payload.CustomerDto;
import com.ecommerce.services.CustomerService;



@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	//POST -create user
	@PostMapping("/")
	public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto ){
		
		CustomerDto createCustomerDto = this.customerService.registerNewCustomer (customerDto);
		return new ResponseEntity<>(createCustomerDto, HttpStatus.CREATED);
	}
	
	
	//PUT -update user
	
	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto,@PathVariable Integer customerId){
		
		CustomerDto updatedCustomer =	this.customerService.updateCustomer(customerDto, customerId);
		return ResponseEntity.ok(updatedCustomer);
	}
	
	//Delete - delete user
	@DeleteMapping("/{customerId}")
	public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("customerId") Integer uid){
		this.customerService.deleteCustomer(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer deleted Successfully",true), HttpStatus.OK);
	}
 
	//Get -user get
	@GetMapping("/")
	public ResponseEntity<List<CustomerDto>> getAllCustomers(){
		return ResponseEntity.ok(this.customerService.getAllCustomers());
	}
	
	//Get -user get
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getSingleCustomer(@PathVariable Integer customerId){
		return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
		
	}	
}
