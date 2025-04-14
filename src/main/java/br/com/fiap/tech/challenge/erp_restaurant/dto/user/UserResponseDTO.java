package br.com.fiap.tech.challenge.erp_restaurant.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {

	String name;

	String email;

	String login;
}
