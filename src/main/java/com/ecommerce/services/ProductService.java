package com.ecommerce.services;

import java.util.List;

import com.ecommerce.models.ProductDetails;
import com.ecommerce.payload.ProductDto;
import com.ecommerce.payload.ProductResponse;

public interface ProductService {
	
	
	//create
	ProductDto createProduct(ProductDto property , Integer ownerId, Integer locationId);
	
	// update 
	ProductDto updateProduct(ProductDto property , Integer id);
	
	
	// get by id 
	ProductDto getProductById(Integer id);
	
	// get all product
    ProductResponse getAllProducts(Integer pageNumber , Integer pageSize, String sortBy, String sortDir );
	
	// delete product
	void deleteProduct(Integer id);
	
	// get property by  category
	
	List<ProductDto> getProductByCategoryDetails(Integer locationId);
	
	// get post by owner 
	
	List<ProductDto> getProductByOwner(Integer ownerId);
	
	// search property 
	
	List<ProductDto> searchProductDetails(String keyword);

	List<ProductDto>  getAllProductDetails();


	List<ProductDetails> findAll();




}
