package br.com.fiap.tech.challenge.erp_restaurant.infrastructure.controller.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import br.com.fiap.tech.challenge.erp_restaurant.application.exception.AuthenticationException;
import br.com.fiap.tech.challenge.erp_restaurant.application.exception.NotFoundException;
import br.com.fiap.tech.challenge.erp_restaurant.application.exception.RoleException;

@ControllerAdvice
public class ExceptionHandlerController {

	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorMessage> handleNotFoundException(NoResourceFoundException ex){
		return ResponseEntity.status(404).body(new ErrorMessage("No signs of humanity here"));	
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex){
		return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));	
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException ex){
		return ResponseEntity.status(401).body(new ErrorMessage(ex.getMessage()));	
	}
	
	@ExceptionHandler(RoleException.class)
	public ResponseEntity<ErrorMessage> handleRoleException(RoleException ex){
		return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handleNotFoundException(MethodArgumentNotValidException ex){
		List<String> errors = new ArrayList<>();
		for(var error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() +" "+ error.getDefaultMessage());
		}
		return ResponseEntity.status(400).body(errors);	
	}
}
