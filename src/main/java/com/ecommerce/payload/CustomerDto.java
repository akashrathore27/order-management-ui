package com.ecommerce.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {
	
	
	private int id;
	@NotEmpty
	@Size(min=4, message = "Username must be min of 4 characters !!")
	private String name;
	private String contactdetail;
	@NotEmpty
	@Size(min  = 3 , max = 10 , message = "Password must be min of 3 chars and max of 10")
	private String password;	
	
	private Set<RoleDto> role = new HashSet<>();
}