package br.com.fiap.tech.challenge.erp_restaurant.controller.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import br.com.fiap.tech.challenge.erp_restaurant.exception.NotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NoResourceFoundException ex){
		return ResponseEntity.status(404).body("No signs of humanity here");	
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException ex){
		return ResponseEntity.status(404).body(ex.getMessage());	
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
