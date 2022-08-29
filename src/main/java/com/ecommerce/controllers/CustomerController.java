package com.ecommerce.controllers;

import java.io.FileNotFoundException;
import java.util.List;

import javax.validation.Valid;

import com.ecommerce.services.impl.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.payload.ApiResponse;
import com.ecommerce.payload.CustomerDto;
import com.ecommerce.services.CustomerService;



@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private ReportService reportService;
	
	//POST -create user


	@PostMapping("/")
	public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto ){
		
		CustomerDto createCustomerDto = this.customerService.registerNewCustomer (customerDto);
		return new ResponseEntity<>(createCustomerDto, HttpStatus.CREATED);
	}

	//generated report

	@GetMapping("/report/{format}")
	public String generatedReport(@PathVariable  String format) throws JRException, FileNotFoundException {
	return  reportService.exportReportForCustomer(format);
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
