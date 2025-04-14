package br.com.fiap.tech.challenge.erp_restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.tech.challenge.erp_restaurant.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> , PagingAndSortingRepository<User, String>{

	
}