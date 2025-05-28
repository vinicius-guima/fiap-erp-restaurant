package br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.address;

import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.user.UserResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressResponseDTO {
    private Long id;
    private String state;
    private String city;
    private String street;
    private int number;
    private String complement;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private UserResponseDTO user;

    public UserResponseDTO getUser() {
        return UserResponseDTO.builder().email(this.user.getEmail()).build();
    }
}
