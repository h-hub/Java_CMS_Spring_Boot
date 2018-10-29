package com.hhub.controlller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hhub.model.User;
import com.hhub.model.dto.UserDto;
import com.hhub.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
	    UserDto userDto = new UserDto();
	    model.addAttribute("userDto", userDto);
	    return "user/login";
	}
	
	@GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }
	
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

	private User createUser(UserDto userDto) {
		
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPassword( new BCryptPasswordEncoder().encode(userDto.getPassword()));
		user.setRole("ADMIN");
		userService.create(user);
		return user;
		
	}
	
}
