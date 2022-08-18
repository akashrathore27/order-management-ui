package com.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.payload.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.models.CategoryDetails;
import com.ecommerce.models.Owner;
import com.ecommerce.models.ProductDetails;
import com.ecommerce.repository.CategoryRepo;
import com.ecommerce.repository.OwnerRepo;
import com.ecommerce.repository.ProductRepo;

import com.ecommerce.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OwnerRepo ownerRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public ProductDto createProduct(ProductDto productDto, Integer ownerId, Integer categoryId) {
		
		Owner owner = this.ownerRepo.findById(ownerId).orElseThrow(()-> new ResourceNotFoundException("Owner","Owner id", ownerId)); 
		CategoryDetails category =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryDetails","category id", categoryId));
		ProductDetails product =this.modelMapper.map(productDto, ProductDetails.class);
		product.setOwner(owner);
		product.setCategory(category);
	    product.setImageName("default.png");
	    ProductDetails newProduct= this.productRepo.save(product);
		return this.modelMapper.map(newProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer id) {
	ProductDetails product=	this.productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("ProductDetails","id", id));

	product.setName(productDto.getName());
	product.setCategorytype(productDto.getCategorytype());
	product.setBrand(productDto.getBrand());
	product.setPrice((productDto.getPrice()));
	product.setDescription((productDto.getDescription()));
	product.setImageName(productDto.getImageName());

   ProductDetails updatedProduct= this.productRepo.save(product);
		return this.modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Integer id) {
	   ProductDetails product=  this.productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("ProductDetails","id", id));
		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ProductResponse getAllProducts(Integer pageNumber , Integer pageSize, String sortBy, String sortDir) {
		Sort sort =null;
		if(sortDir.equalsIgnoreCase("asc")){
			sort=Sort.by(sortBy).ascending();
		}else {
			
			 sort=Sort.by(sortBy).descending();
		}
		
		Pageable p= PageRequest.of(pageNumber, pageSize,sort);
		Page<ProductDetails> pageProduct=this.productRepo.findAll(p);
		List<ProductDetails> allProperties =pageProduct.getContent();
		
	List<ProductDto> productDtos = allProperties.stream().map((property)-> this.modelMapper.map(property, ProductDto.class ))
			.collect(Collectors.toList());
	
	ProductResponse productResponse =new ProductResponse();
	productResponse.setDetails(productDtos);
	productResponse.setPageNumber(pageProduct.getNumber());
	productResponse.setPageSize(pageProduct.getSize());
	productResponse.setTotalElements(pageProduct.getTotalElements());
	productResponse.setTotalPages(pageProduct.getTotalPages());
	productResponse.setLastPage(pageProduct.isLast());

	return productResponse;
	}

	@Override
	public void deleteProduct(Integer id) {
	ProductDetails product =this.productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("ProductDetails","id", id));
       this.productRepo.delete( product );
	}

	@Override
	public List<ProductDto> getProductByCategoryDetails(Integer categoryId) {
		CategoryDetails category =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryDetails","categoryId", categoryId));
		List<ProductDetails> products=this.productRepo.findByCategory(category);
		List<ProductDto> productDtos =	products.stream().map((product)-> this.modelMapper.map(product, ProductDto.class )).collect(Collectors.toList());
		return productDtos;
	}
 
	@Override
	public List<ProductDto> getProductByOwner(Integer ownerId) {
		Owner owner= this.ownerRepo.findById(ownerId).orElseThrow(()-> new ResourceNotFoundException("Owner","ownerId", ownerId));
		
		List<ProductDetails> products=this.productRepo.findByOwner(owner);
		List<ProductDto> productDtos =	products.stream().map((product)-> this.modelMapper.map(product, ProductDto.class )).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public List<ProductDto> searchProductDetails(String keyword) {
		List<ProductDetails> products= this.productRepo.searchProductByCategoryType("%"+keyword+"%");
		List<ProductDto> productDtos = products.stream().map((product)-> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public List<ProductDto> getAllProductDetails() {

		List<ProductDetails>  products  = this.productRepo.findAll();
		List<ProductDto>  productDtos =  products.stream()
				.map( product-> this.productToDto(product)).collect(Collectors.toList());

		return productDtos;

	}
//
//	@Override
//	public List<OwnerDto> getAllOwners() {
//
//		List<Owner>  owners  = this.OwnerRepo.findAll();
//		List<OwnerDto>  ownerDtos =  owners.stream()
//				.map( owner-> this.ownerToDto(owner)).collect(Collectors.toList());
//
//		return ownerDtos;
//
//	}
	private ProductDetails dtoToProduct(ProductDto productDto) {

		ProductDetails product = this.modelMapper.map(productDto, ProductDetails.class);

		return product;
	}

	public ProductDto productToDto(ProductDetails product) {

		ProductDto productDto = this.modelMapper.map(product, ProductDto.class);

		return productDto;
	}


	@Override
	public List<ProductDetails> findAll() {
		return productRepo.findAll();
	}


}

	



	

	


