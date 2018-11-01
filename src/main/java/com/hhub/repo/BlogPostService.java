package com.hhub.repo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhub.model.BlogPost;
import com.hhub.model.User;

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

		return blogPostRepository.findAll();
		
	}
}
