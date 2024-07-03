package com.tyss.blogapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.blogapplication.payloads.UserDto;
import com.tyss.blogapplication.response.Response;
import com.tyss.blogapplication.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/saveUser")
	public ResponseEntity<Response> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto userSaved = userService.createUser(userDto);
		return new ResponseEntity<Response>(new Response(false, "saved successfully", userSaved), HttpStatus.CREATED);

	}

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<Response> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updatedUser = userService.updateUser(userDto, userId);
		return new ResponseEntity<Response>(new Response(false, "updated successfully", updatedUser),
				HttpStatus.CREATED);
	}

	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<Response> getUserById(@PathVariable Integer userId) {
		UserDto userById = userService.getUserById(userId);
		return new ResponseEntity<Response>(new Response(false, "got user successfully", userById), HttpStatus.OK);

	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<Response> getAllUsers() {

		List<UserDto> allUsers = userService.getAllUsers();
		return new ResponseEntity<Response>(new Response(false, "got all user successfully", allUsers), HttpStatus.OK);

	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}
}
