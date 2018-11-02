package com.hhub.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User implements IUser {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(nullable = false)
    private String firstName;
    
	@Column(nullable = false)
    private String lastName;
    
	@Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @ManyToOne
    @JoinColumn(name="role_id", nullable=false)
    private Role Role;
    
    @Column(nullable = false, name="ACTIVE")
    private boolean status;
    
    @OneToMany(mappedBy="editor")
    private Set<BlogPost> posts;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return Role;
	}

	public void setRole(Role role) {
		Role = role;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<BlogPost> getPosts() {
		return posts;
	}

	public void setPosts(Set<BlogPost> posts) {
		this.posts = posts;
	}    
}
