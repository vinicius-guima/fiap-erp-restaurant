package br.com.fiap.tech.challenge.erp_restaurant.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.tech.challenge.erp_restaurant.entity.Address;
import br.com.fiap.tech.challenge.erp_restaurant.entity.User;
import br.com.fiap.tech.challenge.erp_restaurant.exception.NotFoundException;
import br.com.fiap.tech.challenge.erp_restaurant.repository.AddressRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AddressService {
	
	private final AddressRepository addressRepository;
	
	private final UserService userService;
	
	
	
	public AddressService(AddressRepository addressRepository, UserService userService) {
		this.addressRepository = addressRepository;
		this.userService = userService;
	}

	public Address getAddressById(Long id){
		return addressRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Address not found here"));		 
	}
	
	public Address save(Address a) {
		log.info("saving addres {}" , a.getStreet() );
		return addressRepository.save(a);
	}
	
	public Address updateUserAddress(String email, Long idAddress) {
		User user = userService.findUserById(email);
		Address address = this.getAddressById(idAddress);
		address.setUser(user);
		return this.addressRepository.save(address);
	}

	public Address update(Address a) {
		log.info("updating address {}", a.getStreet());

		Optional<Address> address = addressRepository.findById(a.getId());
		if (address.isEmpty())
			throw new NotFoundException("Address not found here");

		return addressRepository.save(a);
	}
	
}
