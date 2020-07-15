package com.example.tfmagazine.domain.enums;

public enum StatePayment {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String description;
	
	private StatePayment(int cod, String description) {
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
	
	public static StatePayment toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (StatePayment x : StatePayment.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido "+cod);
		
	}

}
