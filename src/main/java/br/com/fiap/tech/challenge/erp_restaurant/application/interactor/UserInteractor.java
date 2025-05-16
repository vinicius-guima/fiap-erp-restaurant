package br.com.fiap.tech.challenge.erp_restaurant.application.interactor;

import br.com.fiap.tech.challenge.erp_restaurant.application.domain.Address;
import br.com.fiap.tech.challenge.erp_restaurant.application.domain.User;
import br.com.fiap.tech.challenge.erp_restaurant.application.exception.NotFoundException;
import br.com.fiap.tech.challenge.erp_restaurant.application.exception.NotUniqueException;
import br.com.fiap.tech.challenge.erp_restaurant.application.gateway.AddressGateway;
import br.com.fiap.tech.challenge.erp_restaurant.application.gateway.UserGateway;
import br.com.fiap.tech.challenge.erp_restaurant.application.usecase.UserUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserInteractor implements UserUseCase {

    private final AddressGateway addressGateway;

    private final UserGateway userGateway;

    public UserInteractor(UserGateway userGateway, AddressGateway addressGateway) {
        this.userGateway = userGateway;
        this.addressGateway = addressGateway;
    }

    public List<User> findAllUsers(int page, int size) {
        return userGateway.findAllUsers(size, page);
    }

    public User findUserById(Long id) {
        return userGateway.findById(id).orElseThrow(() -> new NotFoundException("User not found here"));
    }

    public User save(User userToSave) {
        boolean emailAlreadyInUse = userGateway.existsByEmail(userToSave.getEmail());
        if (emailAlreadyInUse) {
            throw new NotUniqueException("This email is already been used by another user");
        }

        boolean loginAlreadyInUse = userGateway.existsByLogin(userToSave.getLogin());
        if (loginAlreadyInUse) {
            throw new NotUniqueException("This login is already been used by another user");
        }

        log.info("saving user {}", userToSave.getLogin());
        User savedUser = userGateway.save(new User(null, userToSave.getName(), userToSave.getEmail(), userToSave.getLogin(), userToSave.getPassword(), userToSave.getRole()));
        return User.builder().id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .login(savedUser.getLogin())
                .role(savedUser.getRole())
                .updatedAt(savedUser.getUpdatedAt())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    public User update(User userToUpdate) {
        Optional<User> user = userGateway.findById(userToUpdate.getId());
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        // ToDo: Deal with duplicated email and login on update

        log.info("updating user {}", userToUpdate.getLogin());

        User savedUser = userGateway.save(new User(userToUpdate.getId(), userToUpdate.getName(), userToUpdate.getEmail(), userToUpdate.getLogin(), userToUpdate.getPassword(), userToUpdate.getRole()));
        return User.builder().id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .login(savedUser.getLogin())
                .role(savedUser.getRole())
                .updatedAt(savedUser.getUpdatedAt())
                .createdAt(savedUser.getCreatedAt())
                .build();
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
