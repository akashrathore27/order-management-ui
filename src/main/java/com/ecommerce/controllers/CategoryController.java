package com.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.payload.ApiResponse;
import com.ecommerce.payload.CategoryDto;
import com.ecommerce.services.CategoryServices;


@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private CategoryServices categoryServices;

	// POST-create product
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createProduct(@Valid @RequestBody CategoryDto productDto) {

		CategoryDto createProductDto = this.categoryServices.createCategory(productDto);
		return new ResponseEntity<>(createProductDto, HttpStatus.CREATED);
	}

	// update

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateProduct(@Valid @RequestBody CategoryDto productDto,
													  @PathVariable Integer categoryId) {

		CategoryDto updatedProductDto = this.categoryServices.updateCategory(productDto, categoryId);
		return  ResponseEntity.ok(updatedProductDto);
	}

	// Delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer categoryId) {
		this.categoryServices.deleteCategory(categoryId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("category Deleted Successfully", true), HttpStatus.OK);
	}

	// Get -user get
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories() {
		return ResponseEntity.ok(this.categoryServices.getCategories());
	}

	// Get -user get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
		CategoryDto categoryDto = this.categoryServices.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);

	}

}
