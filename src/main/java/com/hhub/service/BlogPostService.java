package com.hhub.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hhub.model.BlogPost;
import com.hhub.model.dto.BlogDto;
import com.hhub.util.BlogStatus;

public interface BlogPostService {

	BlogPost create(BlogPost blogPost);

	Optional<BlogPost> findById(Integer blogId);

	List<BlogPost> getAll();

	void archiveBlogPost();
	
	String createImage(String name, MultipartFile file, String path) throws Exception;
	
	BlogPost createBlogPost(BlogDto blogDto, String imagePath) throws ParseException;
	
	void changeStatus(Integer blogPostId, Boolean approve, String date) throws ParseException;
	
}