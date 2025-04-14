package br.com.fiap.tech.challenge.erp_restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.fiap.tech.challenge.erp_restaurant.dto.user.UserRequestDTO;
import br.com.fiap.tech.challenge.erp_restaurant.entity.User;

@Mapper
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
	
	User dtoToObject (UserRequestDTO u);
	

	
}
