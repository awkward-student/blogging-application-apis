package com.app.blog.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);
	
}
