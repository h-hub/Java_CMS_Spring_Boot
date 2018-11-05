package com.hhub.service;

import java.util.Optional;

import com.hhub.model.User;
import com.hhub.model.dto.UserDto;

public interface UserService {

	User create(User user);

	Iterable<User> getAll();

	void changeStatus(Integer userId, boolean status);

	Optional<User> getUserById(Integer userId);

	User findUserByEmail(String email);
	
	User createUser(UserDto userDto);

}