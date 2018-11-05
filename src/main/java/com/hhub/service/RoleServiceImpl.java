package com.hhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhub.model.Role;
import com.hhub.repo.RoleRepository;

@Service
public class RoleServiceImpl implements RolesService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	/* (non-Javadoc)
	 * @see com.hhub.service.IRolesService#findByRole(java.lang.String)
	 */
	@Override
	public Role findByRole(String role) {
		
		return roleRepository.findByRole(role);
		
	}
	
}
