package com.hhub.model.validators;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckDateValidator implements ConstraintValidator<CheckDateFormat, String> {

    private String pattern;

    @Override
    public void initialize(CheckDateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }

        try {
            Date date = new SimpleDateFormat(pattern).parse(object);
            
            if (date.before(new Date())) {
            	return false;
            } else {
            	return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
