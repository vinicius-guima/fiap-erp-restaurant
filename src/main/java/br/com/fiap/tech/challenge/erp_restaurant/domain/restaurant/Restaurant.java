package br.com.fiap.tech.challenge.erp_restaurant.domain.restaurant;

import java.time.LocalTime;

import br.com.fiap.tech.challenge.erp_restaurant.domain.address.Address;
import br.com.fiap.tech.challenge.erp_restaurant.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Restaurant {

	private Long id;
	private String name;
	private String kitchenType;
	private LocalTime openingAt;
	private LocalTime closingAt;

	private User owner;
	private Address address;

	public void setName(String name) {
		this.name = name;
	}

	public void setKitchenType(String kitchenType) {
		this.kitchenType = kitchenType;
	}

	public void setOpeningAt(LocalTime openingAt) {
		this.openingAt = openingAt;
	}

	public void setClosingAt(LocalTime closingAt) {
		this.closingAt = closingAt;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setOwnerId(Long ownerId) {
		this.owner = new User(ownerId);
	}

	public void setAddressId(Long addressId) {
		if (addressId != null) {
			this.address = new Address(addressId);
		}
	}
	

}
