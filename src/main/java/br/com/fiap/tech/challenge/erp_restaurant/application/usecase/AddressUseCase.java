package br.com.fiap.tech.challenge.erp_restaurant.application.usecase;

import br.com.fiap.tech.challenge.erp_restaurant.application.domain.Address;

public interface AddressUseCase {

    Address getAddressByUser(Long id);

    Address create(Address a);

    Address update(Address a);

    Address updateUserAddress(Long idUser, Long idAddress);
}
