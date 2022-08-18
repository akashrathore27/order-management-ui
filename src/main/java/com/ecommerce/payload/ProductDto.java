 package com.ecommerce.payload;

import javax.validation.constraints.NotNull;

import com.ecommerce.models.Customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

	
	private int id;
	@NotNull


	private String name;

	@NotNull
	private String categorytype;
	
	@NotNull
	private String brand;
	
	@NotNull
	private String price ;
	
	@NotNull
	private String description;

	@NotNull
	private String imageName;
	
	private OwnerDto owner ;
	
	private CategoryDto category;
	
	private Customer customer;
	
	
	
}
