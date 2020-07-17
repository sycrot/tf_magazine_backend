package com.example.tfmagazine.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfmagazine.domain.Category;
import com.example.tfmagazine.domain.Product;
import com.example.tfmagazine.repositories.CategoryRepository;
import com.example.tfmagazine.repositories.ProductRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Category cat1 = new Category(null, "Eletrônicos");
		Category cat2 = new Category(null, "Informática");
		Category cat3 = new Category(null, "Esporte e lazer");
		Category cat4 = new Category(null, "Ferramentas");
		
		Product p1 = new Product(null, "Notebook", 2100.00);
		Product p2 = new Product(null, "Impressora", 1400.00);
		Product p3 = new Product(null, "Mouse", 40.00);
		Product p4 = new Product(null, "Bola futebol", 120.00);
		Product p5 = new Product(null, "Chave philips", 60.00);
		Product p6 = new Product(null, "Bola basquete", 140.00);
		Product p7 = new Product(null, "Kit ferraments", 400.00);
		
		cat1.getProducts().addAll(Arrays.asList(p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat3.getProducts().addAll(Arrays.asList(p4, p6));
		cat4.getProducts().addAll(Arrays.asList(p5, p7));
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat2));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1, cat2));
		p4.getCategories().addAll(Arrays.asList(cat3));
		p5.getCategories().addAll(Arrays.asList(cat4));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
		
		
	}

}
