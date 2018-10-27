package com.hhub.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.hhub.model.User;

public class BlogDto {
	
	@NotNull
    @NotEmpty
	private String title;
	
	@NotNull
    @NotEmpty
	private String content;
	
	@NotNull
    @NotEmpty
	private String path;
	
	@NotNull
    @NotEmpty
	private User editor;

	public void setTitle(String title) {
		
		this.title = title;
		
	}

	public String getTitle() {

		return title;
		
	}

	public void setContent(String content) {

		this.content = content;
		
	}

	public String getContent() {

		return content;
		
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setEditor(User editor) {
		
		this.editor = editor;
		
	}

	public User getEditor() {

		return editor;
		
	}

}
