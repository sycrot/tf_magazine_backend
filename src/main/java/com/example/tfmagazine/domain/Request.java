package com.example.tfmagazine.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Request implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instant;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="request")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="delivery_address_id")
	private Address addressClient;
	
	@OneToMany(mappedBy="id.request")
	private Set<ItemRequest> items = new HashSet<>();

	public Request() {
	}

	public Request(Integer id, Date instant, Client client, Address addressClient) {
		super();
		this.id = id;
		this.instant = instant;
		this.client = client;
		this.addressClient = addressClient;
	}
	
	public double getAmount() {
		
		double sum = 0.0;
		for (ItemRequest ir : items) {
			sum += ir.getSubTotal();
		}
		
		return sum;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment pagamento) {
		this.payment = pagamento;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAddressClient() {
		return addressClient;
	}

	public void setAddressClient(Address addressClient) {
		this.addressClient = addressClient;
	}

	public Set<ItemRequest> getItems() {
		return items;
	}

	public void setItems(Set<ItemRequest> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstant()));
		builder.append(", Cliente: ");
		builder.append(getClient().getNome());
		builder.append(", Situação do pagamento: ");
		builder.append(getPayment().getState().getDescription());
		builder.append("\nDetalhes:\n");
		for (ItemRequest ip : getItems()) {
			builder.append(ip.toString());
		}
		
		builder.append("Valor total: ");
		builder.append(nf.format(getAmount()));
		
		return builder.toString();
		
	}
	
	
	
	
	
	
	

}
