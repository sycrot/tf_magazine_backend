package com.example.tfmagazine.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfmagazine.domain.City;
import com.example.tfmagazine.domain.State;
import com.example.tfmagazine.dto.CityDTO;
import com.example.tfmagazine.dto.StateDTO;
import com.example.tfmagazine.services.CityService;
import com.example.tfmagazine.services.StateService;

@RestController
@RequestMapping(value="/estados")
public class StateResource {
	
	@Autowired
	private StateService service;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll() {
		
		List<State> list = service.findAll();
		List<StateDTO> listDTO = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	@RequestMapping(value="/{stateId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId) {
		
		List<City> list = cityService.findByState(stateId);
		List<CityDTO> listDto = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}

}
