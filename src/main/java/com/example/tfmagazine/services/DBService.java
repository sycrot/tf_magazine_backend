package com.example.tfmagazine.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfmagazine.domain.Category;
import com.example.tfmagazine.repositories.CategoryRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Category cat1 = new Category(null, "Eletrônicos");
		Category cat2 = new Category(null, "Informática");
		Category cat3 = new Category(null, "Esporte e lazer");
		Category cat4 = new Category(null, "Ferramentas");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		
	}

}
