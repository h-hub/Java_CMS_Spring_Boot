package com.hhub.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhub.model.BlogPost;
import com.hhub.model.User;
import com.hhub.repo.BlogPostRepository;
import com.hhub.util.BlogStatus;

@Service
public class BlogPostService {

	@Autowired
	private BlogPostRepository blogPostRepository;
	
	public BlogPost create(BlogPost blogPost) {
		
        return blogPostRepository.save(blogPost);
        
    }

	public Optional<BlogPost> findById(Integer blogId) {

		return blogPostRepository.findById(blogId);

	}

	public Iterable<BlogPost> getAll() {

		return blogPostRepository.findByOrderByIdDesc();
		
	}

	public void changeStatus(Integer blogPostId, BlogStatus status) {


		blogPostRepository.updateBlogPostSetStatusById(status, blogPostId);
		
	}

	public void changeStatus(Integer blogPostId, BlogStatus status, Date pubToDate) {

		blogPostRepository.updateBlogPostSetStatusById(status, blogPostId, pubToDate);
		
	}

	public void archiveBlogPost() {

		blogPostRepository.archiveBlogPost(BlogStatus.ARCHIVED, new Date());
		
	}
}
