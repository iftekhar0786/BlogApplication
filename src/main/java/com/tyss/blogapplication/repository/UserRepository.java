package com.tyss.blogapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.blogapplication.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String username);

}
