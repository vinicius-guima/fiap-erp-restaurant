package br.com.fiap.tech.challenge.erp_restaurant.controller.v1;

import static br.com.fiap.tech.challenge.erp_restaurant.mapper.AddressMapper.INSTANCE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech.challenge.erp_restaurant.dto.address.AddressRequestDTO;
import br.com.fiap.tech.challenge.erp_restaurant.dto.address.AddressResponseDTO;
import br.com.fiap.tech.challenge.erp_restaurant.service.AddressService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("v1/address")
public class AddressControllerV1 {
	
	private final AddressService addressService;
	

	public AddressControllerV1(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping("{id}")
	public AddressResponseDTO getAddress(@PathVariable("id") Long id){
		return INSTANCE.objetcToDto(addressService.getAddressById(id));
	}
	
	@PostMapping
	public AddressResponseDTO createAddress(@RequestBody AddressRequestDTO address) {
		log.info("receiving address to create" , address);
		return INSTANCE.objetcToDto(
				this.addressService.save(INSTANCE.dtoToObject(address))) ;
	}
	
	@PutMapping
	public AddressResponseDTO updateAddress(@RequestBody AddressRequestDTO dto) {
		log.info("receiving update address {}" , dto);
		return INSTANCE.objetcToDto(addressService.update(INSTANCE.dtoToObject(dto))) ;
	}
	
	@PutMapping("/user")
	public AddressResponseDTO updateUserAddress(
			@RequestParam("user") String user,
			@RequestParam("address") Long address
			) {
		log.info("receiving user {} to update address {}" , user, address);
		return INSTANCE.objetcToDto(addressService.updateUserAddress(user, address)) ;
	}
	
}
