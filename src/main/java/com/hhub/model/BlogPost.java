package com.hhub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.hhub.util.BlogStatus;

@Entity
public class BlogPost implements IBlogPost {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private String imagePath;
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private User editor;
	
	@Column(nullable = false)
	private BlogStatus blogStatus;
	
	@Column(nullable = false)
	private Date publishedToDate;

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
	
	

}
