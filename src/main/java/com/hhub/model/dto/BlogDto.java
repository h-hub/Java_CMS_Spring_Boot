package com.hhub.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.hhub.model.User;

public class BlogDto {
	
	@NotNull
    @NotEmpty
	private String title;
	
	@NotNull
    @NotEmpty
	private String content;
	
	@NotNull
	private MultipartFile  image;
	

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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
