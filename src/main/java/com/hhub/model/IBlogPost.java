package com.hhub.model;

import java.util.Date;

import com.hhub.util.BlogStatus;

public interface IBlogPost {
	
	public void setTitle(String title);
	
	public String getTitle();
	
	public void setContent(String content);
	
	public String getContent();
	
	public void setImage(String path);
	
	public String getImage();
	
	public void setEditor(User editor);
	
	public User getEditor();
	
	public void setStatus(BlogStatus blogStatus);
	
	public BlogStatus getStatus();
	
	public void setPublishedToDate(Date publishedToDate);

	public Date getPublishedToDate();
}
