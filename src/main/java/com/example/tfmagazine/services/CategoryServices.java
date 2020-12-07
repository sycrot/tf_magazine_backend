package com.example.tfmagazine.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.tfmagazine.domain.Category;
import com.example.tfmagazine.dto.CategoryDTO;
import com.example.tfmagazine.repositories.CategoryRepository;
import com.example.tfmagazine.services.exception.DataIntegrityException;
import com.example.tfmagazine.services.exception.ObjectNotFoundException;

@Service
public class CategoryServices {
	
	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		
		Optional<Category> obj = repo.findById(id);
		
		return obj.orElseThrow(
				() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " +id+ ", Tipo: "+Category.class.getName()));
		
	}
	
	public Category insert(Category obj) {
		
		obj.setId(null);
		return repo.save(obj);
		
	}
	
	public Category update(Category obj) {
		
		Category newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
	
	public void delete(Integer id) {
		
		find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possível excluir uma categoria que tem produtos");
		}
	}
	
	public List<Category> findAll() {
		return repo.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Category fromDTO(CategoryDTO obj) {
		return new Category(obj.getId(), obj.getName());
	}
}
