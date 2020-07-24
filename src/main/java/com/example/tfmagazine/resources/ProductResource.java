package com.example.tfmagazine.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfmagazine.domain.Product;
import com.example.tfmagazine.dto.ProductDTO;
import com.example.tfmagazine.resources.utils.URL;
import com.example.tfmagazine.services.ProductServices;

@RestController
@RequestMapping(value="/produtos")
public class ProductResource {
	
	@Autowired
	private ProductServices service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Product> find(@PathVariable Integer id) {
		
		Product obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
	
		String nomeDecod = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Product> list = service.search(nomeDecod, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDTO = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	

}
