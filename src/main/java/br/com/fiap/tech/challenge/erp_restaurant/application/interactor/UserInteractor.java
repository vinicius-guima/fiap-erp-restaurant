package br.com.fiap.tech.challenge.erp_restaurant.application.interactor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.tech.challenge.erp_restaurant.application.domain.Address;
import br.com.fiap.tech.challenge.erp_restaurant.application.domain.User;
import br.com.fiap.tech.challenge.erp_restaurant.application.exception.NotFoundException;
import br.com.fiap.tech.challenge.erp_restaurant.application.gateway.AddressGateway;
import br.com.fiap.tech.challenge.erp_restaurant.application.gateway.UserGateway;
import br.com.fiap.tech.challenge.erp_restaurant.application.usecase.UserUseCase;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserInteractor implements UserUseCase{

    private final AddressGateway addressGateway;

	private final UserGateway userGateway;

	public UserInteractor(UserGateway userGateway, AddressGateway addressGateway) {
		this.userGateway = userGateway;
		this.addressGateway = addressGateway;
	}

	public List<User> findAllUsers(int page, int size) {
		return userGateway.findAllUsers(size,page);
	}

	public User findUserById(Long id) {
		return userGateway.findById(id).orElseThrow(() -> new NotFoundException("User not found here"));
	}

	public User save(User u) {
		log.info("saving user {}", u.getLogin());
		User saved = userGateway.save(new User(null, u.getName(), u.getEmail(), u.getLogin(), u.getPassword(),u.getRole()));
		return User.builder().id(saved.getId())
				.name(saved.getName())
				.email(saved.getEmail())
				.login(saved.getLogin())
				.role(saved.getRole())
				.lastupdate(saved.getLastupdate())
				.build();
	}

	public User update(User u) {
		log.info("updating user {}", u.getLogin());

		if (!userGateway.existsByEmail(u.getEmail()))
			throw new NotFoundException("User not found here");

		return save(u);
	}

	public void delete(Long id) {
		log.info("deleting user id {}", id);

		Optional<User> user = userGateway.findById(id);
		if (user.isEmpty())
			throw new NotFoundException("User not found here");
		
		Address userAddress = addressGateway.findByUserId(id);
		if(userAddress != null)
			addressGateway.delete(userAddress);

		userGateway.delete(id);
	}

}
