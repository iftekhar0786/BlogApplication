package com.tyss.blogapplication.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tyss.blogapplication.exception.CategoryNotFoundException;
import com.tyss.blogapplication.exception.InvalidCredentialException;
import com.tyss.blogapplication.exception.PostNotFoundException;
import com.tyss.blogapplication.exception.UserNotFoundException;
import com.tyss.blogapplication.response.JwtAuthResponse;
import com.tyss.blogapplication.response.Response;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response> handler(UserNotFoundException e) {
		return new ResponseEntity<Response>(new Response(true, e.getMessage(), null), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<Response> handler(CategoryNotFoundException e) {
		return new ResponseEntity<Response>(new Response(true, e.getMessage(), null), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<Response> handler(PostNotFoundException e) {
		return new ResponseEntity<Response>(new Response(true, e.getMessage(), null), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<JwtAuthResponse> handler(InvalidCredentialException e) {
		return new ResponseEntity<JwtAuthResponse>(new JwtAuthResponse(null, e.getMessage()),HttpStatus.BAD_REQUEST );
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handler(MethodArgumentNotValidException e) {
		Map<String, String> respone = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String defaultMessage = error.getDefaultMessage();
			respone.put(fieldName, defaultMessage);
		});
		return new ResponseEntity<Map<String, String>>(respone, HttpStatus.BAD_REQUEST);
	}
}
