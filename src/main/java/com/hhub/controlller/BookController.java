package com.hhub.controlller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hhub.model.Book;
import com.hhub.model.dto.BookDto;
import com.hhub.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
    
    @GetMapping("/addbook")
    public String addBookForm(Model model) {
    	BookDto bookDto = new BookDto();
        model.addAttribute("bookDto", bookDto);
        return "add_book";
    }
    
    @PostMapping("/addbook")
	public String abbBookSubmit(@ModelAttribute @Valid BookDto bookDto,BindingResult result, Model m) {
    	
    	Book book = new Book();
    	
    	if(result.hasErrors()) {
            m.addAttribute("bookDto", bookDto);
            return "add_book";
        } else {
        	book = createBook(bookDto);
        	bookService.create(book);
        }
    	
    	m.addAttribute("message", "Successfully saved Book: "
    	          + bookDto.toString());		
		return "home";
	}

	private Book createBook(BookDto bookDto) {
		
		return new Book(bookDto.getTitle(),bookDto.getAuthor());
	}
}
