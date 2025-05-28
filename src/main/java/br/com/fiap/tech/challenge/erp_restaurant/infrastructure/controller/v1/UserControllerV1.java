package br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.v1;

import br.com.fiap.tech.challenge.erp_restaurant.application.usecase.UserUseCase;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.user.UserRequestDTO;
import br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.dto.user.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fiap.tech.challenge.erp_restaurant.mapper.UserMapper.INSTANCE;

@Log4j2
@RestController
@RequestMapping("v1/users")
public class UserControllerV1 {


    private final UserUseCase userUseCase;

    public UserControllerV1(UserUseCase userService) {
        this.userUseCase = userService;
    }


    @GetMapping
    public List<UserResponseDTO> getUsers(
            @RequestParam int page,
            @RequestParam int size) {
        return userUseCase.findAllUsers(page, size).stream().map(INSTANCE::domainToDTO).toList();
    }

    @GetMapping("{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return INSTANCE.domainToDTO(userUseCase.findUserById(id));
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO userDto) {
        log.info("receiving user to create", userDto);
        return INSTANCE.domainToDTO(userUseCase.save(INSTANCE.dtoToDomain(userDto)));
    }

    @PutMapping
    public UserResponseDTO updateUser(@RequestBody @Valid UserRequestDTO userDto) {
        log.info("receiving user to update", userDto);
        return INSTANCE.domainToDTO(userUseCase.update(INSTANCE.dtoToDomain(userDto)));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) {
        log.info("receiving user id {} to delete", id);
        userUseCase.delete(id);
    }

}
