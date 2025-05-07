package br.com.fiap.tech.challenge.erp_restaurant.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.persistence.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long>{

	@Query("select a from AddressEntity a "
			+ "join UserEntity u on a.user = a.user "
			+ "where a.user.id = :id")
	AddressEntity findByUserId(@Param("id") Long id);

}
