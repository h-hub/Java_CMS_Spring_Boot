package com.hhub.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(nullable = false)
    private String role;
	
	@OneToMany(mappedBy="role")
    private Set<User> users;

	public Role(String role) {
		super();
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return role;
	}

	public void setName(String role) {
		this.role = role;
	}

	public Set<User> getUsers() {
		return users;
	}
	
}
