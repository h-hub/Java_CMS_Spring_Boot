package com.hhub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

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
	private String path;
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private User editor;

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
	public void setImage(String path) {

		this.path = path;
		
	}

	@Override
	public String getImage() {

		return path;
		
	}

	@Override
	public void setEditor(User editor) {
		
		this.editor = editor;
		
	}

	@Override
	public User getEditor() {

		return editor;
		
	}
	
	

}
