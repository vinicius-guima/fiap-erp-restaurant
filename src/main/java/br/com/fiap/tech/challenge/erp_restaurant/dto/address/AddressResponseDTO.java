package br.com.fiap.tech.challenge.erp_restaurant.dto.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fiap.tech.challenge.erp_restaurant.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressResponseDTO {
	private Long id;
	private String state;
	private String city;
	private String street;
	private int number;
	private String complement;
	private User user;
}
