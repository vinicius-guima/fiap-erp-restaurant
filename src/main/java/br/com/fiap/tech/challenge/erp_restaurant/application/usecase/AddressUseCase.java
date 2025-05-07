package br.com.fiap.tech.challenge.erp_restaurant.application.usecase;

import br.com.fiap.tech.challenge.erp_restaurant.application.domain.Address;

public interface AddressUseCase {

	public Address getAddressByUser(Long id);
	
	public Address save(Address a);
	
	public Address updateUserAddress(Long idUser, Long idAddress);
}
