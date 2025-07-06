package br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.user;

import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.address.AddressRequestDTO;

public record UserUpdateAddressRequestDTO(

		int id,

		AddressRequestDTO address) {

}
