package com.example.tfmagazine.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.tfmagazine.domain.Category;

public class CategoryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=2, max=100, message="O tamanho deve ser entre 2 e 80 caracteres")
	private String name;

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(Category obj) {
		id = obj.getId();
		name = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
