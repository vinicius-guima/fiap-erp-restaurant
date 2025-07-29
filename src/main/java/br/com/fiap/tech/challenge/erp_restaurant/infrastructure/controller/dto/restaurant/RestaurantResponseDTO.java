package br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.restaurant;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.address.AddressResponseDTO;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.user.UserResponseDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantResponseDTO {
	
	private Long id;
	private String name;
	private String kitchenType;
	private LocalTime openingAt;
	private LocalTime closingAt;
	private AddressResponseDTO address;
	private String ownerName;
	private Long ownerId;
	private Long addressId;
}
