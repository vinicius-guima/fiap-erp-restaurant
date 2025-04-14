package br.com.fiap.tech.challenge.erp_restaurant.controller.v1;

import static br.com.fiap.tech.challenge.erp_restaurant.mapper.UserMapper.INSTANCE;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech.challenge.erp_restaurant.dto.user.UserRequestDTO;
import br.com.fiap.tech.challenge.erp_restaurant.dto.user.UserResponseDTO;
import br.com.fiap.tech.challenge.erp_restaurant.entity.User;
import br.com.fiap.tech.challenge.erp_restaurant.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("v1/users")
public class UserControllerV1 {

	
	private final UserService userService;
	
	public UserControllerV1(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping
	public Page<User> getUsers(
			@RequestParam("page") int page, @RequestParam("size")int size){
		return userService.findAllUsers(page, size);
	}
	


	@GetMapping("{email}")
	public User getUser(@PathVariable("email") String email){
		return userService.findUserById(email);
	}
	
	@PostMapping
	public UserResponseDTO createUser(@RequestBody  @Valid UserRequestDTO userDto) {
		log.info("receiving user to create" , userDto);
		return userService.save(userDto);
	}

	@PutMapping
	public UserResponseDTO updateUser(@RequestBody @Valid UserRequestDTO userDto) {
		log.info("receiving user to update" , userDto);
		return userService.update(userDto) ;
	}
	
	@DeleteMapping
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteUser(@RequestBody UserRequestDTO userDto) {
		log.info("receiving user to delete" , userDto);
		userService.delete(INSTANCE.dtoToObject(userDto));
	}

}
