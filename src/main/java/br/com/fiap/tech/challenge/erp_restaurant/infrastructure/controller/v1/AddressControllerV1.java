package br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.v1;

import static br.com.fiap.tech.challenge.erp_restaurant.mapper.AddressMapper.INSTANCE;

import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.address.AddressRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.tech.challenge.erp_restaurant.application.usecase.address.AddressUseCase;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.address.AddressResponseDTO;
import lombok.extern.log4j.Log4j2;
@Log4j2
@RestController
@RequestMapping("v1/address")
public class AddressControllerV1 {

    private final AddressUseCase addressUseCase;

    public AddressControllerV1(AddressUseCase addressService) {
        this.addressUseCase = addressService;
    }

    @GetMapping("{id}")
    public AddressResponseDTO findById(@PathVariable Long id) {
        return INSTANCE.toResponse(addressUseCase.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponseDTO create(@RequestBody AddressRequestDTO dto) {
        var address = addressUseCase.create(INSTANCE.toDomain(dto));
        return INSTANCE.toResponse(address);
    }

    @PutMapping("{id}")
    public AddressResponseDTO update(@PathVariable Long id, @RequestBody AddressRequestDTO dto) {
        var updated = addressUseCase.update(id, INSTANCE.toDomain(dto));
        return INSTANCE.toResponse(updated);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        addressUseCase.delete(id);
    }
}
