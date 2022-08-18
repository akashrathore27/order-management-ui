package com.ecommerce.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ecommerce.models.ProductDetails;
import com.ecommerce.payload.*;
import com.ecommerce.repository.ProductRepo;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.config.AppConstants;
import com.ecommerce.services.FileService;
import com.ecommerce.services.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// create product
	@PostMapping("/owner/{ownerId}/category/{categoryId}/products")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,
													 @PathVariable Integer ownerId, @PathVariable Integer categoryId)

	{
		ProductDto createProperty = this.productService.createProduct(productDto, ownerId, categoryId);
		return new ResponseEntity<ProductDto>(createProperty, HttpStatus.CREATED);
	}

	// get by owner
	@GetMapping("/owner/{ownerId}/products")
	public ResponseEntity<List<ProductDto>> getProductByOwner(@PathVariable Integer ownerId) {
		List<ProductDto> products = this.productService.getProductByOwner(ownerId);

		return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
	}

	// get by category
	@GetMapping("/category/{categoryId}/products")
	public ResponseEntity<List<ProductDto>> getProductByCategoryDetails(@PathVariable Integer categoryId) {
		List<ProductDto> products = this.productService.getProductByCategoryDetails(categoryId);

		return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
	}

	// get all product
	@GetMapping("/products")
	public ResponseEntity<ProductResponse> getAllProducts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		ProductResponse productResponse = this.productService.getAllProducts(pageNumber, pageSize, sortBy,
				sortDir);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
	}


	// get product by id

	@GetMapping("/products/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id) {
		ProductDto product = this.productService.getProductById(id);
		return new ResponseEntity<ProductDto>(product, HttpStatus.OK);

	}

	// delete product

	@DeleteMapping("/products/{id}")
	public ApiResponse deleteProduct(@PathVariable Integer id) {

		this.productService.deleteProduct(id);
		return new ApiResponse("product is successfully deleted !!" + id, true);

	}

	// update product
	@PutMapping("/products/{id}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer id) {
		ProductDto updatedProduct = this.productService.updateProduct(productDto, id);

		return new ResponseEntity<ProductDto>(updatedProduct, HttpStatus.OK);
	}

	@GetMapping("/products/search/{keywords}")
	public ResponseEntity<List<ProductDto>> searchProductDetails(@PathVariable("keywords") String keywords

	) {
		List<ProductDto> result = this.productService.searchProductDetails(keywords);
		return new ResponseEntity<List<ProductDto>>(result, HttpStatus.OK);
	}

	// product image upload
	@PostMapping("/product/image/upload/{id}")
	public ResponseEntity<ProductDto> uploadPostImage(@RequestParam("image") MultipartFile image,
													  @PathVariable Integer id) throws IOException {
         ProductDto productDto = this.productService.getProductById(id);
		String fileName = this.fileService.UploadImage(path, image);
		
		productDto.setImageName(fileName);

	ProductDto updateProduct=	this.productService.updateProduct(productDto, id);
	return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);
	}
	
	// serve image files 
	
	@GetMapping(value="/product/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response
			
			) throws IOException {
		
		InputStream resource =this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
		
	}

//	@GetMapping("/productlist")
//	public ResponseEntity<List<ProductDto>> getAllProductDetails(){
//		return ResponseEntity.ok(this.productService.getAllProductDetails());
//	}

	@GetMapping("/prod")
	public ResponseEntity<?> getAllProducts() {
		List<ProductDetails> product= productService.findAll();

		return new ResponseEntity<>(product, HttpStatus.OK);
	}

}
