package com.hhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhub.model.Role;
import com.hhub.repo.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role findByRole(String role) {
		
		return roleRepository.findByRole(role);
		
	}
	
}
