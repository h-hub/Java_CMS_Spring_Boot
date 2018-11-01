package com.hhub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.hhub.util.BlogStatus;

@Entity
public class BlogPost implements IBlogPost, BaseModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private String imagePath;
	
	@ManyToOne
    @JoinColumn(name="editor", nullable=false)
	private User editor;
	
	@Column(nullable = false,  name="STATUS")
	private BlogStatus blogStatus;
	
	@Column(nullable = false, name="PUB_TO_DATE")
	private Date publishedToDate;
	
	@Column(nullable = false, name="CREATED_BY")
	private String createdBy;
	
	@Column(nullable = false, name="CREATE_TIME")
	private Date createDate;
	
	@Column(nullable = false, name="MODIFY_BY")
	private String modifyBy;
	
	@Column(nullable = false, name="MODIFY_TIME")
	private Date modifyDate;
	
	

	public int getId() {
		return id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public BlogStatus getBlogStatus() {
		return blogStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	@Override
	public void setTitle(String title) {
		
		this.title = title;
		
	}

	@Override
	public String getTitle() {

		return title;
		
	}

	@Override
	public void setContent(String content) {

		this.content = content;
		
	}

	@Override
	public String getContent() {

		return content;
		
	}

	@Override
	public void setImage(String imagePath) {

		this.imagePath = imagePath;
		
	}

	@Override
	public String getImage() {

		return imagePath;
		
	}

	@Override
	public void setEditor(User editor) {
		
		this.editor = editor;
		
	}

	@Override
	public User getEditor() {

		return editor;
		
	}

	@Override
	public void setStatus(BlogStatus blogStatus) {
		
		this.blogStatus = blogStatus;
		
	}

	@Override
	public BlogStatus getStatus() {

		return blogStatus;
	}

	@Override
	public void setPublishedToDate(Date publishedToDate) {
		
		this.publishedToDate = publishedToDate;
		
	}

	@Override
	public Date getPublishedToDate() {
		
		return publishedToDate;
		
	}

	@Override
	public void setCreateBy(String userEmail) {
		
		this.createdBy = userEmail;
		
	}

	@Override
	public String getCreateBy() {

		return createdBy;
	}

	@Override
	public void setCreateTime(Date createDate) {

		this.createDate = createDate;
		
	}

	@Override
	public Date getCreateTime() {

		return createDate;
		
	}

	@Override
	public void setModifyBy(String userEmail) {

		this.modifyBy = userEmail;
		
	}

	@Override
	public String getModifyBy() {

		return modifyBy;
	}

	@Override
	public void setModifyTime(Date modifyDate) {

		this.modifyDate = modifyDate;
		
	}

	@Override
	public Date getModifyTime() {
		
		return modifyDate;
		
	}
	
	

}
