package br.com.fiap.tech.challenge.erp_restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tech.challenge.erp_restaurant.entity.Address;
import br.com.fiap.tech.challenge.erp_restaurant.entity.User;

public interface AddressRepository extends JpaRepository<Address, Long>{

	
	List<Address> findByUser(User user);

}
