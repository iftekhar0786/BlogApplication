package com.tyss.blogapplication.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.blogapplication.entity.User;
import com.tyss.blogapplication.exception.UserNotFoundException;
import com.tyss.blogapplication.payloads.UserDto;
import com.tyss.blogapplication.repository.UserRepository;
import com.tyss.blogapplication.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

// save user
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = User.builder().name(userDto.getName()).email(userDto.getEmail()).about(userDto.getAbout())
				.password(userDto.getPassword()).build();

		userRepository.save(user);

		return userDto;
	}

// update user
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User userFromDb = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("user Not found"));

//		userFromDb.setPassword(userDto.getPassword());
//		userFromDb.setAbout(userDto.getAbout());
//		userFromDb.setEmail(userDto.getEmail());
//		userFromDb.setName(userDto.getName());
		BeanUtils.copyProperties(userDto, userFromDb);

		userRepository.save(userFromDb);
		return userDto;
	}

//get user by id	
	@Override
	public UserDto getUserById(Integer userId) {
		Optional<User> userOptionalEntity = userRepository.findById(userId);

		if (!userOptionalEntity.isPresent()) {
			throw new UserNotFoundException("user not found");

		} else {
			User user = userOptionalEntity.get();
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			return userDto;
		}

	}

//get all users
	@Override
	public List<UserDto> getAllUsers() {
		List<User> usersFromDB = userRepository.findAll();

		List<UserDto> userDtolist = new ArrayList<UserDto>();
		if (usersFromDB == null || usersFromDB.isEmpty()) {
			throw new UserNotFoundException("user not found");
		} else {
			usersFromDB.stream().forEach(i -> {
				UserDto userDto = new UserDto();
				BeanUtils.copyProperties(i, userDto);
				userDtolist.add(userDto);
			});
		}
		return userDtolist;
	}

//delete user by id	
	@Override
	public void deleteUser(Integer userId) {
		Optional<User> userOptionalEntity = userRepository.findById(userId);

		if (!userOptionalEntity.isPresent()) {
			throw new UserNotFoundException("user not found");

		}
		userRepository.deleteById(userId);

	}

}