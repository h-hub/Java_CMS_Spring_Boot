package com.hhub.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.hhub.model.validators.ContentType;
import com.hhub.util.BlogStatus;

@ContentType({ "image/jpg", "image/jpeg", "image/png", "image/gif" })
public class BlogDto {

    private int id;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String content;

    private MultipartFile image;

    private String imagePath;

    private boolean preview;

    private boolean save;

    private BlogStatus status = BlogStatus.NEW;
    
    private String editorFirstName;
    
    private String editorLastName;

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

    public BlogStatus getStatus() {
	return status;
    }

    public void setStatus(BlogStatus status) {
	this.status = status;
    }

    public String getEditorFirstName() {
        return editorFirstName;
    }

    public void setEditorFirstName(String editorFirstName) {
        this.editorFirstName = editorFirstName;
    }

    public String getEditorLastName() {
        return editorLastName;
    }

    public void setEditorLastName(String editorLastName) {
        this.editorLastName = editorLastName;
    }

}
