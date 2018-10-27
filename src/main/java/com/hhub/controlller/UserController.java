package com.hhub.controlller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hhub.model.User;
import com.hhub.model.dto.UserDto;

@Controller
public class UserController {
	
	@GetMapping("/registerUser")
	public String showRegistrationForm(Model model) {
	    UserDto userDto = new UserDto();
	    model.addAttribute("userDto", userDto);
	    return "user/registration";
	}
	
	@PostMapping("/registerUser")
	public String registerUserSubmit(@ModelAttribute @Valid UserDto userDto,BindingResult result, Model m) {
		
		User user = new User();
		
		if(result.hasErrors()) {
            m.addAttribute("userDto", userDto);
            return "user/registration";
        } else {
        	user = createUser(userDto);
        }
		
		m.addAttribute("userDto", userDto);
		return "user/registration";
		
	}

	private User createUser(@Valid UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
