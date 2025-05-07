package br.com.fiap.tech.challenge.erp_restaurant.infrastructure.repository;

import static br.com.fiap.tech.challenge.erp_restaurant.mapper.AddressMapper.INSTANCE;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.tech.challenge.erp_restaurant.application.domain.Address;
import br.com.fiap.tech.challenge.erp_restaurant.application.gateway.AddressGateway;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.persistence.AddressEntity;

@Component
public class AddressGatewayImp implements AddressGateway {

	private final AddressRepository addressRepository;

	public AddressGatewayImp(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public Optional<Address> findById(Long id) {
		Optional<AddressEntity> a = addressRepository.findById(id);
		return a.isPresent() ? Optional.ofNullable(INSTANCE.toDomain(a.get())) : Optional.empty();
	}

	public Address findByUserId(Long id) {
		return INSTANCE.toDomain(addressRepository.findByUserId(id));
	}

	public Address save(Address userAddres) {
		return INSTANCE.toDomain(addressRepository.save(INSTANCE.toEntity(userAddres)));
	}

	public void delete(Address userAddres) {
		addressRepository.delete(INSTANCE.toEntity(userAddres));
	}

}