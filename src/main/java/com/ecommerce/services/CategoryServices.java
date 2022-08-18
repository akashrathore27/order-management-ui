package com.ecommerce.services;

import java.util.List;


import com.ecommerce.payload.CategoryDto;



public interface CategoryServices {

	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	
	void deleteCategory(Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	
	//get All
	List<CategoryDto> getCategories();
	
	
}
