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
import com.ecommerce.payload.CategoryDto;
import com.ecommerce.services.CategoryServices;


@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

	@Autowired
	private CategoryServices categoryServices;

	@Autowired
	private ReportService reportService;
	// POST-create product
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto productDto) {

		CategoryDto createProductDto = this.categoryServices.createCategory(productDto);
		return new ResponseEntity<>(createProductDto, HttpStatus.CREATED);
	}
	@GetMapping("/report/{format}")
	public String generatedReport(@PathVariable  String format) throws JRException, FileNotFoundException {
		return  reportService.exportReportForCategory(format);
	}


	// update

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto productDto,
													  @PathVariable Integer categoryId) {

		CategoryDto updatedProductDto = this.categoryServices.updateCategory(productDto, categoryId);
		return  ResponseEntity.ok(updatedProductDto);
	}

	// Delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
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
