package com.hhub.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.hhub.model.validators.CheckDateFormat;
import com.hhub.model.validators.ContentType;

public class BlogDto {
	
	private int id;
	
	@NotNull
    @NotEmpty
	private String title;
	
	@NotNull
    @NotEmpty
	private String content;
	
	@NotNull
	@ContentType({"image/jpg","image/jpeg","image/png","image/gif"})
	private MultipartFile  image;
	
	private String imagePath;
	
	@CheckDateFormat(pattern = "MM/dd/yyyy")
	private String publishedToDate;
	
	private boolean preview;
	
	private boolean save;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getPublishedToDate() {
		
		return publishedToDate;
		
	}

	public void setPublishedToDate(String publishedToDate) {
		
		this.publishedToDate = publishedToDate;
		
	}

	public boolean isPreview() {
		return preview;
	}

	public void setPreview(boolean preview) {
		this.preview = preview;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

}
