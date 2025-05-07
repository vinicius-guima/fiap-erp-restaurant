package br.com.fiap.tech.challenge.erp_restaurant.application.usecase;

import java.util.List;

import br.com.fiap.tech.challenge.erp_restaurant.application.domain.User;

public interface UserUseCase{

	List<User> findAllUsers(int page, int size);
	
	User findUserById(Long id);
	
	User save(User u);
	
	User update(User u);
	
	void delete(Long id);
}
