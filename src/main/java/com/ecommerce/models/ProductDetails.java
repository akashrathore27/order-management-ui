package com.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="product_details")
@NoArgsConstructor
@Getter
@Setter
public class ProductDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="Name")
	private String name ;
	
	@Column(name="category_type")
    private String categorytype;
	
	@Column(name="brand")
    private String brand;
	
	@Column(name="price")
    private String price ;

	@Column(name="description")
	private String description ;
	
	@Column(name="image_name")
	private String imageName;
	

	
	@ManyToOne
	
	@JoinColumn(name="Owner_id")
	private Owner owner;



	@JsonIgnore
	@ManyToOne
	
	@JoinColumn(name="Category_id")
	private CategoryDetails  category;

	@JsonIgnore
	@ManyToOne

	@JoinColumn(name="customer_id")
	private Customer customer;


	
}
