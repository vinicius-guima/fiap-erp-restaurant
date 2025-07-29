package br.com.fiap.tech.challenge.erp_restaurant.mapper;

import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.restaurant.RestaurantRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.fiap.tech.challenge.erp_restaurant.domain.restaurant.Restaurant;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.restaurant.RestaurantResponseDTO;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.persistence.RestaurantEntity;

@Mapper
public interface RestaurantMapper {

	RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

	@Mapping(target = "owner.id" , source = "ownerId")
	@Mapping(target = "address.id" , source = "addressId")
	Restaurant dtoToDomain(RestaurantRequestDTO dto);
 
	@Mapping(target = "ownerName" , source = "owner.name")
	@Mapping(target = "ownerId" , source = "owner.id")
	@Mapping(target = "addressId" , source = "address.id")
	@Mapping(target = "address", ignore = true)
	RestaurantResponseDTO domainToDTO(Restaurant domain);
	@Mapping(target = "ownerName", source = "owner.name")
	@Mapping(target = "ownerId" , source = "owner.id")
	@Mapping(target = "addressId", ignore = true)
	@Mapping(target = "address", source = "address")
	RestaurantResponseDTO toGetResponseDTO(Restaurant restaurant);

	RestaurantEntity domainToEntity(Restaurant restaurant);  

	Restaurant entityToDomain(RestaurantEntity r); 

}
