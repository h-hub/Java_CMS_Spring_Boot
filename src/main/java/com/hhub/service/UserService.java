package com.hhub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhub.model.User;
import com.hhub.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User create(User user) {
		
        return userRepository.save(user);
        
    }

	public Iterable<User> getAll() {

        return userRepository.findAll();

	}

}
