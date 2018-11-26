package com.hhub.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.hhub.model.BlogPost;
import com.hhub.model.dto.BlogDto;

public interface BlogPostService {

	BlogPost create(BlogPost blogPost);

	BlogPost findById(Integer blogId) throws Exception;

	List<BlogDto> getAll();

	void archiveBlogPost();
	
	String createImage(String name, MultipartFile file, String path) throws Exception;
	
	BlogDto createBlogPost(BlogDto blogDto, String imagePath) throws ParseException, Exception;
	
	void changeStatus(Integer blogPostId, Boolean approve, String date) throws ParseException;
	
	boolean exists(Integer blogPostId);

	BlogDto findByIdtoShow(Integer blogId) throws Exception;
	
}