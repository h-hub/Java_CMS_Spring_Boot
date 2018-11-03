package com.hhub.model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hhub.model.User;
import com.hhub.service.UserService;

public class EmailExistValidator implements ConstraintValidator<EmailExist, String> {
	
	@Autowired
	private UserService userService;

	@Override
	public void initialize(EmailExist constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return (validateEmail(email));
	}

	private boolean validateEmail(String email) {
		
		User user = userService.findUserByEmail(email);
		
		if(user ==null) {
			return true;
		}
		
		return false;
	}

}
