package br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.address;

import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.user.UserRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressRequestDTO(
        Long id,
        String state,
        String city,
        String street,
        int number,
        String complement,
        UserRequestDTO user) {
}
