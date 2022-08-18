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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="Category_details")
@NoArgsConstructor
@Getter
@Setter
public class CategoryDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name="categoryName" , nullable= false)
	private String categoryName;
	

	
	
	@OneToMany(mappedBy = "category", cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ProductDetails> product = new ArrayList<>();



	

}
