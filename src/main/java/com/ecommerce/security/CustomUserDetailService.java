package com.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.models.Customer;
import com.ecommerce.repository.CustomerRepo;



@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private CustomerRepo customerRepo;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Customer customer = this.customerRepo.findBycontactdetail(username).orElseThrow(()-> new ResourceNotFoundException("Customer", "contactDetail", username));
		
		return customer;
	}
	
	
}