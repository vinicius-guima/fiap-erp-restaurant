package br.com.fiap.tech.challenge.erp_restaurant.application.interactor;

import java.util.List;

import br.com.fiap.tech.challenge.erp_restaurant.application.usecase.address.AddressUseCase;
import br.com.fiap.tech.challenge.erp_restaurant.domain.address.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.tech.challenge.erp_restaurant.application.exception.NotFoundException;
import br.com.fiap.tech.challenge.erp_restaurant.application.exception.RoleException;
import br.com.fiap.tech.challenge.erp_restaurant.application.gateway.restaurant.RestaurantGateway;
import br.com.fiap.tech.challenge.erp_restaurant.application.usecase.restaurant.RestaurantUseCase;
import br.com.fiap.tech.challenge.erp_restaurant.application.usecase.user.UserUseCase;
import br.com.fiap.tech.challenge.erp_restaurant.domain.restaurant.Restaurant;
import br.com.fiap.tech.challenge.erp_restaurant.domain.user.User;
import br.com.fiap.tech.challenge.erp_restaurant.shared.Role;

@Service
public class RestaurantInteractor implements RestaurantUseCase {

	private final UserUseCase userUseCase;
	private final AddressUseCase addressUseCase;

	private final RestaurantGateway restaurantGateway;

	public RestaurantInteractor(UserUseCase userUseCase, AddressUseCase addressUseCase, RestaurantGateway restaurantGateway) {
		super();
		this.userUseCase = userUseCase;
		this.addressUseCase = addressUseCase;
		this.restaurantGateway = restaurantGateway;
	}

	@Override
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		restaurant.setOwner(isUserOwner(restaurant.getOwner().getId()));
		if (hasValidAddress(restaurant.getAddress())) {
			Address fullAddress = addressUseCase.findById(restaurant.getAddress().getId());
			restaurant.setAddress(fullAddress);
		}

		return restaurantGateway.save(restaurant);
	}

	private boolean hasValidAddress(Address address) {
		return address != null && address.getId() != null;
	}

	@Override
	public Restaurant findById(Long id) {
		return restaurantGateway.findById(id).orElseThrow(() -> new NotFoundException("Restaurant not found"));
	}

	@Override
	public List<Restaurant> findAll(int page, int size) {
		return restaurantGateway.findAll(page, size);
	}


	private User isUserOwner(Long userId) {
		User user = userUseCase.findUserById(userId);
		if (!user.getRole().equals(Role.OWNER))
			throw new RoleException("user id {" + userId + "} is not OWNER");
		return user;
	}

	@Override
	public Restaurant update(Long id, Restaurant restaurantNew) {
		Restaurant existing = findById(id);

		existing.setName(restaurantNew.getName());
		existing.setKitchenType(restaurantNew.getKitchenType());
		existing.setOpeningAt(restaurantNew.getOpeningAt());
		existing.setClosingAt(restaurantNew.getClosingAt());
		existing.setOwnerId(restaurantNew.getOwner().getId());
		existing.setAddressId(restaurantNew.getAddress().getId());

		return restaurantGateway.save(existing);
	}

	@Override
	public void delete(Long id) {
		this.findById(id);
		restaurantGateway.delete(id);
	}

}
