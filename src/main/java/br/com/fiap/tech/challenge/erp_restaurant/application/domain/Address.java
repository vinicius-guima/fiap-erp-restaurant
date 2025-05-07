package br.com.fiap.tech.challenge.erp_restaurant.application.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
	
	private Long id;
	private String state;
	private String city;
	private String street;
	private int number;
	private String complement;
	private User user;
	
	public Address( String state, String city, String street, int number, String complement, User user) {
		this.state = state;
		this.city = city;
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.user = user;
	}
}
