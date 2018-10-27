package com.hhub.model;

public interface IBlogPost {
	
	public void setTitle(String title);
	
	public String getTitle();
	
	public void setContent(String content);
	
	public String getContent();
	
	public void setImage(String path);
	
	public String getImage();
	
	public void setEditor(User editor);
	
	public User getEditor();

}
