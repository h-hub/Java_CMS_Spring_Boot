package com.hhub.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("error", "Please upload jpg, png or gif which are less than 5MB in size");
        return "redirect:/add_blog_post";

    }

    @ExceptionHandler(PostNotFoundException.class)
    public String handleError2(PostNotFoundException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("error", "Blog post not found in our Website");
        return "redirect:/global_error";

    }
    
    @ExceptionHandler(RuntimeException.class)
    public String handleError2(RuntimeException e, RedirectAttributes redirectAttributes) {

        return "redirect:/global_error";

    }
    
    @ExceptionHandler(Exception.class)
    public String handleError2(Exception e, RedirectAttributes redirectAttributes) {

        return "redirect:/global_error";

    }

}
