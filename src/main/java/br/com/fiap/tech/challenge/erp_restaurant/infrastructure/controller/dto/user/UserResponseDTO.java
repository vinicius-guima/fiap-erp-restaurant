package br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fiap.tech.challenge.erp_restaurant.shared.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDTO {

	String id;
	
	String name;

	String email;

	String login;
	
	LocalDateTime lastupdate;
	
	Role role;
	
}
