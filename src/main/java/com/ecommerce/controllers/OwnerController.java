package com.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.payload.ApiResponse;
import com.ecommerce.payload.OwnerDto;
import com.ecommerce.services.OwnerService;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = "*")
public class OwnerController {
	
	@Autowired
	private OwnerService OwnerService;
	
	//POST -create user
	@PostMapping("/")
	public ResponseEntity<OwnerDto> createOwner(@Valid @RequestBody OwnerDto OwnerDto ){
		
		OwnerDto createOwnerDto = this.OwnerService.createOwner(OwnerDto);
		return new ResponseEntity<>(createOwnerDto, HttpStatus.CREATED);
	}
	
	
	//PUT -update user
	
	@PutMapping("/{OwnerId}")
	public ResponseEntity<OwnerDto> updateOwner(@Valid @RequestBody OwnerDto OwnerDto,@PathVariable Integer OwnerId){
		
		OwnerDto updatedOwner =	this.OwnerService.updateOwner(OwnerDto, OwnerId);
		return ResponseEntity.ok(updatedOwner);
	}
	
	//Delete - delete user
	@DeleteMapping("/{OwnerId}")
	public ResponseEntity<ApiResponse> deleteOwner(@PathVariable("OwnerId") Integer uid){
		this.OwnerService.deleteOwner(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Owner deleted Successfully",true), HttpStatus.OK);
	}
 
	//Get -user get
	@GetMapping("/")
	public ResponseEntity<List<OwnerDto>> getAllOwners(){
		return ResponseEntity.ok(this.OwnerService.getAllOwners());
	}
	
	//Get -user get
	@GetMapping("/{OwnerId}")
	public ResponseEntity<OwnerDto> getSingleOwner(@PathVariable Integer OwnerId){
		return ResponseEntity.ok(this.OwnerService.getOwnerById(OwnerId));
		
	}	
}

