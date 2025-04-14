package br.com.fiap.tech.challenge.erp_restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.fiap.tech.challenge.erp_restaurant.dto.address.AddressRequestDTO;
import br.com.fiap.tech.challenge.erp_restaurant.dto.address.AddressResponseDTO;
import br.com.fiap.tech.challenge.erp_restaurant.entity.Address;

@Mapper
public interface AddressMapper {

	AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );
	
	Address dtoToObject (AddressRequestDTO u);
	
	AddressResponseDTO objetcToDto(Address a);
}
