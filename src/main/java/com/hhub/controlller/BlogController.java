package com.hhub.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hhub.model.dto.BlogDto;
import com.hhub.model.dto.UserDto;

@Controller
public class BlogController {
	
	@GetMapping("/add_blog_post")
	public String showRegistrationForm(Model model) {
		BlogDto blogDto = new BlogDto();
	    model.addAttribute("blogDto", blogDto);
	    return "blog/add_blog_post";
	}

}
