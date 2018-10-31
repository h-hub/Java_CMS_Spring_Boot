package com.hhub.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhub.model.BlogPost;

@Service
public class BlogPostService {

	@Autowired
	private BlogPostRepository BlogPostRepository;
	
	public BlogPost create(BlogPost blogPost) {
		
        return BlogPostRepository.save(blogPost);
        
    }
}
