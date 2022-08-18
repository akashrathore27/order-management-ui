package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.models.Owner;


public interface OwnerRepo extends JpaRepository<Owner, Integer>  {

}
