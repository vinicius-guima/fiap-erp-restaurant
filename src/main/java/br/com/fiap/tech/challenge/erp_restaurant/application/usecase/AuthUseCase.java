package br.com.fiap.tech.challenge.erp_restaurant.application.usecase;

import br.com.fiap.tech.challenge.erp_restaurant.application.domain.User;

public interface AuthUseCase {
	
	User authenticate(User user);
}
