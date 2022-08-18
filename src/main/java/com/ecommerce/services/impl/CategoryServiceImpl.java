package com.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.models.CategoryDetails;
import com.ecommerce.payload.CategoryDto;
import com.ecommerce.repository.CategoryRepo;
import com.ecommerce.services.CategoryServices;


@Service
public class CategoryServiceImpl implements CategoryServices {
	
	
	@Autowired
	private CategoryRepo categoryRepo;
	

	@Autowired
	private ModelMapper modelmapper;
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		CategoryDetails category = this.modelmapper.map(categoryDto, CategoryDetails.class);
		CategoryDetails addedCategory = this.categoryRepo.save(category);
		return this.modelmapper.map(addedCategory, CategoryDto.class);
		
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		CategoryDetails category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryDetail", "categoryId", categoryId));

		category.setCategoryName(categoryDto.getCategoryName());

		CategoryDetails updatedCategory =this.categoryRepo.save(category);
		
		return this.modelmapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		CategoryDetails category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryDetail", "category id", categoryId));
		
		this.categoryRepo.delete(category);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		CategoryDetails category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryDetail", "category id", categoryId));
		
		return this.modelmapper.map(category , CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<CategoryDetails> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((category) -> this.modelmapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
		return catDtos;
	}
	
}
	
