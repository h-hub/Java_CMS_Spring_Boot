package com.hhub.model.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE,ANNOTATION_TYPE}) 
@Retention(RUNTIME)
@Constraint(validatedBy = ContentTypeMultipartFileValidator.class)
@Documented
public @interface ContentType {

    String message() default "Please upload  jpg, png or gif and less than 5MB in size";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

     String[] value();
}
