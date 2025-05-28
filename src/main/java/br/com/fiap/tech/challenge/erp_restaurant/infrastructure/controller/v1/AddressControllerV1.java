package br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.v1;

import br.com.fiap.tech.challenge.erp_restaurant.application.usecase.AddressUseCase;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.address.AddressRequestDTO;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.address.AddressResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import static br.com.fiap.tech.challenge.erp_restaurant.mapper.AddressMapper.INSTANCE;

@Log4j2
@RestController
@RequestMapping("v1/address")
public class AddressControllerV1 {

    private final AddressUseCase addressUseCase;

    public AddressControllerV1(AddressUseCase addressService) {
        this.addressUseCase = addressService;
    }


    @GetMapping("{id}")
    public AddressResponseDTO getAddressByUser(@PathVariable Long id) {
        return INSTANCE.objetcToDto(addressUseCase.getAddressByUser(id));
    }

    @PostMapping
    public AddressResponseDTO createAddress(
            @RequestBody AddressRequestDTO address) {
        log.info("receiving address to create", address);
        return INSTANCE.objetcToDto(this.addressUseCase.save(INSTANCE.dtoToObject(address)));
    }

    @PutMapping
    public AddressResponseDTO updateAddress(
            @RequestBody AddressRequestDTO address) {
        log.info("receiving address to update", address);
        return INSTANCE.objetcToDto(this.addressUseCase.save(INSTANCE.dtoToObject(address)));
    }

}
