package com.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.repository.OwnerRepo;
import com.ecommerce.models.Owner;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.payload.OwnerDto;
import com.ecommerce.services.OwnerService;
@Service
public class OwnerServiceImpl implements OwnerService {
	

	@Autowired
	private OwnerRepo  OwnerRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public OwnerDto createOwner(OwnerDto OwnerDto) {
		
		Owner Owner = this.dtoToOwner(OwnerDto);
		Owner savedOwner = this.OwnerRepo.save(Owner);
		return this.ownerToDto(savedOwner);
	}

	@Override
	public OwnerDto updateOwner(OwnerDto OwnerDto, Integer OwnerId) {
		
		Owner Owner = this.OwnerRepo.findById(OwnerId).orElseThrow(()-> new ResourceNotFoundException("Owner","Id",OwnerId));
		
		Owner.setName(OwnerDto.getName());
		Owner.setContact_detail(OwnerDto.getContact_detail());
		Owner.setPassword(OwnerDto.getPassword());
		
		
		Owner updatedOwner = this.OwnerRepo.save(Owner);
		OwnerDto OwnerDto1 = this.ownerToDto(updatedOwner);
		
		return OwnerDto1;
	}

	@Override
	public OwnerDto getOwnerById(Integer ownerId) {
		
		Owner owner = this.OwnerRepo.findById(ownerId)
				.orElseThrow(()-> new ResourceNotFoundException("Owner","ownerId",ownerId));
		return this.ownerToDto(owner);
	}

	@Override
	public List<OwnerDto> getAllOwners() {
		
		List<Owner>  owners  = this.OwnerRepo.findAll();
	    List<OwnerDto>  ownerDtos =  owners.stream()
			.map( owner-> this.ownerToDto(owner)).collect(Collectors.toList());
	    
	    return ownerDtos;
		
	}

	@Override
	public void deleteOwner(Integer ownerId) {
		
		Owner owner = this.OwnerRepo.findById(ownerId)
		.orElseThrow(()-> new ResourceNotFoundException("Owner","ownerId",ownerId));
		
		this.OwnerRepo.delete(owner);

	}
//	
	private Owner dtoToOwner(OwnerDto ownerDto) {
		
		Owner owner = this.modelMapper.map(ownerDto, Owner.class);

		return owner;
	}
	
	public OwnerDto ownerToDto(Owner owner) {
		
		OwnerDto customerDto = this.modelMapper.map(owner, OwnerDto.class); 	
		return customerDto;
	}

}