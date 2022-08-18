package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.models.CategoryDetails;
import com.ecommerce.models.Owner;
import com.ecommerce.models.ProductDetails;

public interface ProductRepo extends JpaRepository<ProductDetails,Integer>{

	List<ProductDetails> findByOwner(Owner owner);
	List<ProductDetails> findByCategory(CategoryDetails category);
	
	@Query("select p from ProductDetails p where p.categorytype like :key")
	List<ProductDetails> searchProductByCategoryType(@Param("key") String categorytype);
	
	
	
	
}
 