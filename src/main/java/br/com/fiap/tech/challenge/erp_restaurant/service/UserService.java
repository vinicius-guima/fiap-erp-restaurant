package br.com.fiap.tech.challenge.erp_restaurant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.tech.challenge.erp_restaurant.dto.user.UserRequestDTO;
import br.com.fiap.tech.challenge.erp_restaurant.dto.user.UserResponseDTO;
import br.com.fiap.tech.challenge.erp_restaurant.entity.Address;
import br.com.fiap.tech.challenge.erp_restaurant.entity.User;
import br.com.fiap.tech.challenge.erp_restaurant.exception.NotFoundException;
import br.com.fiap.tech.challenge.erp_restaurant.repository.AddressRepository;
import br.com.fiap.tech.challenge.erp_restaurant.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService {

    private final AddressRepository addressRepository;

	private final UserRepository repository;

	public UserService(UserRepository repository, AddressRepository addressRepository) {
		this.repository = repository;
		this.addressRepository = addressRepository;
	}

	public Page<User> findAllUsers(int page, int size) {
		Page<User> all = repository.findAll(PageRequest.of(size,page));
		return all;
	}

	public User findUserById(String email) {
		return repository.findById(email).orElseThrow(() -> new NotFoundException("User not found here"));
	}

	public UserResponseDTO save(UserRequestDTO u) {
		log.info("saving user {}", u.login());
		User saved = repository.save(new User(u.name(), u.email(), u.login(), u.password()));
		return UserResponseDTO.builder().name(saved.getName()).email(saved.getEmail()).login(saved.getLogin()).build();
	}

	public UserResponseDTO update(UserRequestDTO u) {
		log.info("updating user {}", u.login());

		if (!repository.existsById(u.email()))
			throw new NotFoundException("User not found here");

		return save(u);
	}

	public void delete(User u) {
		log.info("deleting user {}", u.getEmail());

		if (!repository.existsById(u.getEmail()))
			throw new NotFoundException("User not found here");
		
		List<Address> userAddres = addressRepository.findByUser(u);
		for (Address a : userAddres) {
			addressRepository.delete(a);
		}

		repository.delete(u);
	}

}
