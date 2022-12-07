package com.app.blog.services;

import java.util.List;

import com.app.blog.entities.User;
import com.app.blog.payloads.UserDTO;

public interface UserService {
	
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Integer userId);
	UserDTO getUserByID(Integer usedId);
	List<UserDTO> getAllUsers();
	void deleteUser(Integer userId);
	
}
