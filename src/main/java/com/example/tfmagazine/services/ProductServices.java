package com.example.tfmagazine.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.tfmagazine.domain.Category;
import com.example.tfmagazine.domain.Product;
import com.example.tfmagazine.repositories.CategoryRepository;
import com.example.tfmagazine.repositories.ProductRepository;
import com.example.tfmagazine.services.exception.ObjectNotFoundException;

@Service
public class ProductServices {
	
	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	public Product find(Integer id) {
		
		Optional<Product> obj = repo.findById(id);
		
		return obj.orElseThrow(
				() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " +id+ ", Tipo: "+Product.class.getName()));
		
	}
	
	public Page<Product> search(
			String name,
			List<Integer> ids,
			Integer page,
			Integer linesPerPage,
			String orderBy,
			String direction) {
		
		PageRequest pageRequest = PageRequest.of(
				page, 
				linesPerPage,
				Direction.valueOf(direction),
				orderBy);
		
		List<Category> categories = catRepo.findAllById(ids);
		
		return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
				
		
	}
	
}
