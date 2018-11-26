package com.hhub.util;

import org.springframework.stereotype.Component;

import com.hhub.model.BlogPost;
import com.hhub.model.dto.BlogDto;

@Component
public class BlogMapper {
    
    public BlogDto createDtoFromBlogPost(BlogPost blogPost) {

	BlogDto blogDto = new BlogDto();

	blogDto.setId(blogPost.getId());
	blogDto.setTitle(blogPost.getTitle());
	blogDto.setContent(blogPost.getContent());
	blogDto.setImagePath(blogPost.getImage());
	blogDto.setStatus(blogPost.getBlogStatus());
	blogDto.setEditorFirstName(blogPost.getEditor().getFirstName());
	blogDto.setEditorFirstName(blogPost.getEditor().getLastName());

	return blogDto;

    }
    

}
