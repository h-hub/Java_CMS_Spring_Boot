package com.hhub.model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import com.hhub.model.dto.BlogDto;

public class ContentTypeMultipartFileValidator implements ConstraintValidator<ContentType, Object> {

	private String[] acceptedContentTypes;

	@Override
	public void initialize(ContentType constraintAnnotation) {
		this.acceptedContentTypes = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		
		BlogDto blogDto = (BlogDto) obj;
		MultipartFile value= blogDto.getImage();
		
		if( blogDto.getImagePath() != null  && !blogDto.getImagePath().isEmpty()) {
			return true;
		}
		
		if (value == null || value.isEmpty())
			return false;
		
		if ( ContentTypeMultipartFileValidator.acceptContentType(value.getContentType(), acceptedContentTypes) ) {
			
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
}
