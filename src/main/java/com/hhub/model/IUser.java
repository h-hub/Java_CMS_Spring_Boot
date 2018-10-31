package com.hhub.model;

public interface IUser {
	
	public long getId();

	public void setId(int id);

	public String getFirstName();
	
	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);
	
	public String getPassword();
	
	public void setPassword(String password);

	public String getEmail();
	
	public void setEmail(String email);
	
	public boolean isStatus();
	
	public void setStatus(boolean status);
	
}
