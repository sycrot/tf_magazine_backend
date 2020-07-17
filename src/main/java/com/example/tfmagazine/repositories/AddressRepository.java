package com.example.tfmagazine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfmagazine.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	
}
