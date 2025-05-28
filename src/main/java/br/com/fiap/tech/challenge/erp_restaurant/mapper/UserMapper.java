package br.com.fiap.tech.challenge.erp_restaurant.mapper;

import br.com.fiap.tech.challenge.erp_restaurant.application.domain.User;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.auth.AuthRequestDTO;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.user.UserRequestDTO;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.user.UserResponseDTO;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @ValueMapping(target = "CUSTOMER", source = "CUSTOMER")
    @ValueMapping(target = "OWNER", source = "OWNER")
    User dtoToDomain(UserRequestDTO u);

    User authDtoToDomain(AuthRequestDTO a);

    UserResponseDTO domainToDTO(User a);

    User toDomain(UserEntity u);

    UserEntity toEntity(User a);

}
