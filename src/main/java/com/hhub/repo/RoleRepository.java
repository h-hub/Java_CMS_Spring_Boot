package com.hhub.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hhub.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer>{
	
	Role findByRole(String name);
}
