package com.hhub.controlller;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartException;

import com.hhub.model.User;
import com.hhub.model.dto.BlogDto;
import com.hhub.model.dto.UserDto;

@Controller
public class BlogController {
	
	@GetMapping("/add_blog_post")
	public String showBlogPostForm(Model model) throws Exception {
		
		BlogDto blogDto = new BlogDto();
	    model.addAttribute("blogDto", blogDto);
	    return "blog/add_blog_post";
	    
	}
	
	@PostMapping("/add_blog_post")
	public String addBlogPost(@ModelAttribute @Valid BlogDto blogDto,BindingResult result, Model m)  throws Exception{
		
		return "blog/add_blog_post";
		
	}
	
	@GetMapping("/add_blog_post_error")
	public String showBlogPostFormError() {
		
	    return "blog/add_blog_post_error";
	    
	}
	
}
