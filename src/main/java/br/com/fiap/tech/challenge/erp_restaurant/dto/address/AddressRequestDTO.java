package br.com.fiap.tech.challenge.erp_restaurant.dto.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fiap.tech.challenge.erp_restaurant.entity.User;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressRequestDTO (
	
		Long id,
		
		String state,
		
		String city,
		
		String street,
		
		int number,
		
		String complement,
		
		User user ) {

}
