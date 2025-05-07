package br.com.fiap.tech.challenge.erp_restaurant.application.gateway;

import br.com.fiap.tech.challenge.erp_restaurant.application.domain.Address;

public interface AddressGateway {


	public Address findByUserId(Long id);

	public Address save(Address userAddres);

	public void delete(Address userAddres);
}
