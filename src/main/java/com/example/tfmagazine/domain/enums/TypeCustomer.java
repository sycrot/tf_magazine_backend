package com.example.tfmagazine.domain.enums;

public enum TypeCustomer {
	
	PESSOAFISICA(1, "Pessoa física"),
	PESSOAJURIDICA(2, "Pessoa jurídica");
	
	private int cod;
	private String description;
	
	private TypeCustomer(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static TypeCustomer toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TypeCustomer x : TypeCustomer.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido "+cod);
	}

}
