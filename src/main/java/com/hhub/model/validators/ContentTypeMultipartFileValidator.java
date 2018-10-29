package com.hhub.model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class ContentTypeMultipartFileValidator implements ConstraintValidator<ContentType, MultipartFile> {

	private String[] acceptedContentTypes;

	@Override
	public void initialize(ContentType constraintAnnotation) {
		this.acceptedContentTypes = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty())
			return false;
		
		if ( ContentTypeMultipartFileValidator.acceptContentType(value.getContentType(), acceptedContentTypes) 
				& ContentTypeMultipartFileValidator.acceptFileSize(value)) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}

	private static boolean acceptContentType(String contentType, String[] acceptedContentTypes) {
		
		for (String accept : acceptedContentTypes) {
			if (contentType.equalsIgnoreCase(accept)) {
				return true;
			}
		}

		return false;
	}
	
	private static boolean acceptFileSize(MultipartFile file) {
		
		if(file.getSize() < (5e+6)) {
			return true;
		}
		
		return false;
		
	}
}
