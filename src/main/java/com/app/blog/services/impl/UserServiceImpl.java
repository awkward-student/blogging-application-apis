package com.app.blog.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.blog.entities.User;
import com.app.blog.payloads.UserDTO;
import com.app.blog.repos.UserRepo;
import com.app.blog.services.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUserByID(Integer usedId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

	}
	
	public User dtoToUser(UserDTO dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setAbout(dto.getAbout());
		return user;
	}
	
	public UserDTO userToDto(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
