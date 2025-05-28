package br.com.fiap.tech.challenge.erp_restaurant.application.interactor;

import br.com.fiap.tech.challenge.erp_restaurant.application.domain.Address;
import br.com.fiap.tech.challenge.erp_restaurant.application.domain.User;
import br.com.fiap.tech.challenge.erp_restaurant.application.exception.NotFoundException;
import br.com.fiap.tech.challenge.erp_restaurant.application.gateway.AddressGateway;
import br.com.fiap.tech.challenge.erp_restaurant.application.usecase.AddressUseCase;
import br.com.fiap.tech.challenge.erp_restaurant.application.usecase.UserUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class AddressInteractor implements AddressUseCase {

    private final AddressGateway addressGateway;

    private final UserUseCase userUseCase;

    public AddressInteractor(AddressGateway addressGateway, UserUseCase userService) {
        this.addressGateway = addressGateway;
        this.userUseCase = userService;
    }


    public Address getAddressByUser(Long id) {
        return Optional.ofNullable(addressGateway.findByUserId(id))
                .orElseThrow(() -> new NotFoundException("this address is not here"));
    }

    public Address save(Address a) {
        log.info("saving address {}", a.getStreet());
        User user = userUseCase.findUserById(a.getUser().getId());
        Address userAddress = this.getAddressByUser(a.getUser().getId());

        if (userAddress != null) {
            userAddress.setState(a.getState());
            userAddress.setCity(a.getCity());
            userAddress.setStreet(a.getStreet());
            userAddress.setNumber(a.getNumber());
            userAddress.setComplement(a.getComplement());
            userAddress.setUser(user);
            return addressGateway.save(userAddress);
        } else {
            return addressGateway.save(a);
        }
    }

    public Address updateUserAddress(Long idUser, Long idAddress) {
        User user = userUseCase.findUserById(idUser);
        Address address = this.getAddressByUser(idUser);
        address.setUser(user);
        return this.addressGateway.save(address);
    }


}
