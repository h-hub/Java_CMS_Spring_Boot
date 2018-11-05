package com.hhub.controlller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hhub.model.User;
import com.hhub.model.dto.UserDto;
import com.hhub.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

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

		Iterable<User> userList = userService.getAll();
		model.addAttribute("userList", userList);
		return "user/user_list";
	}

	@PostMapping("/change_user_status")
	public String suspendUser(@RequestParam("userId") Integer userId, @RequestParam("status") Boolean status) {

		userService.changeStatus(userId, status);

		return "redirect:user_list";
	}
}
