package com.example.tfmagazine.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	
	@Column(unique=true)
	private String email;
	private String cpfOuCnpj;
	private int tipo;
	
	@JsonIgnore
	private String senha;
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL)
	private List<Address> adresses = new ArrayList<>();
	

}
