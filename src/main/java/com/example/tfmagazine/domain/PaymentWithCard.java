package com.example.tfmagazine.domain;

import javax.persistence.Entity;

import com.example.tfmagazine.domain.enums.StatePayment;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PaymentWithCard extends Payment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PaymentWithCard() {
		
	}

	public PaymentWithCard(int id, StatePayment state, Request request, Integer numeroDeParcelas) {
		super(id, state, request);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
	
	

}
