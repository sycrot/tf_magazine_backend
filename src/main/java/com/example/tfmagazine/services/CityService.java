package com.example.tfmagazine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfmagazine.domain.City;
import com.example.tfmagazine.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;
	
	public List<City> findByState(Integer stateId) {
		return repo.findCities(stateId);
	}
	
}
