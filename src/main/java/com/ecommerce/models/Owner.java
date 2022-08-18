package com.ecommerce.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import com.property.controllers.CustomerController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="Owner")
@NoArgsConstructor
@Getter 
@Setter
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int ownerId;
	
	@Column(name = "Owner_name" , nullable = false, length = 100)
	private String name;
	private String contact_detail;
	private String password;
	
	@OneToMany(mappedBy = "owner", cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ProductDetails> product = new ArrayList<>();
//	
//	 @OneToMany
//	 private CustomerController customer;


	
	
	

}
