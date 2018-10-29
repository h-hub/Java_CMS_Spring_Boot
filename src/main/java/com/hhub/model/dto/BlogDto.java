package com.hhub.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.hhub.model.validators.CheckDateFormat;
import com.hhub.model.validators.ContentType;

public class BlogDto {
	
	@NotNull
    @NotEmpty
	private String title;
	
	@NotNull
    @NotEmpty
	private String content;
	
	@NotNull
	@ContentType({"image/jpg","image/jpeg","image/png","image/gif"})
	private MultipartFile  image;
	
	@CheckDateFormat(pattern = "MM/dd/yyyy")
	private String publishedToDate;
	

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

	public String getPublishedToDate() {
		
		return publishedToDate;
		
	}

	public void setPublishedToDate(String publishedToDate) {
		
		this.publishedToDate = publishedToDate;
		
	}

}
