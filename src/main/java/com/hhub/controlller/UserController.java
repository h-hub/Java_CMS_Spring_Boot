package com.hhub.controlller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hhub.model.Role;
import com.hhub.model.User;
import com.hhub.model.dto.UserDto;
import com.hhub.service.RolesService;
import com.hhub.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RolesService rolesService;

	@GetMapping("/login")
	public String showLoginForm(@RequestParam(value = "error", required = false) boolean error, Model model) {
		UserDto userDto = new UserDto();

		if (error) {
			model.addAttribute("error", "Username or password error. Try again. Please contact admin if you have been suspended.");
		}

		model.addAttribute("userDto", userDto);
		return "user/login";
	}

	@GetMapping("/add_user")
	public String showRegistrationForm(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "user/add_user";
	}

	@PostMapping("/process_add_user")
	public String registerUserSubmit(@ModelAttribute @Valid UserDto userDto, BindingResult result, Model m) {

		User user = new User();

		if (result.hasErrors()) {

			m.addAttribute("userDto", userDto);

			if (result.getGlobalError() != null) {
				m.addAttribute("globalError", result.getGlobalError().getDefaultMessage());
			}

			return "user/add_user";

		} else {
			user = userService.createUser(userDto);
			m.addAttribute("message", "User was created successfully, with email : " + user.getEmail());
			m.addAttribute("userDto", userDto);
			return "user/add_user";
		}

	}

	@GetMapping("/user_list")
	public String showUserList(Model model) {
		
		List<User> userListSorted = new ArrayList<User>();
		Role role = rolesService.findByRole("EDITOR");
		
		
		Set<User> userList = role.getUsers();
		userListSorted = userList.stream().collect(Collectors.toList());
    	Collections.sort(userListSorted, (o1, o2) -> o2.getId() - o1.getId());
    	
    	model.addAttribute("userList", userListSorted);
    	
		return "user/user_list";
	}

	@PostMapping("/change_user_status")
	public String suspendUser(@RequestParam("userId") Integer userId, @RequestParam("status") Boolean status) {

		userService.changeStatus(userId, status);

		return "redirect:user_list";
	}
	
	
}
