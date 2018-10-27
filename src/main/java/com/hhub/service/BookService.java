package com.hhub.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhub.exception.BookNotFoundException;
import com.hhub.model.Book;
import com.hhub.repo.BookRepository;

@Service
public class BookService {
	
	@Autowired
    private BookRepository bookRepository;
 
    public Iterable findAll() {
        return bookRepository.findAll();
    }
 
    public List findByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }
 
    public Book findOne(Long id) {
        return bookRepository.findById(id)
        			.orElseThrow(() -> new BookNotFoundException("Book not found", new EntityNotFoundException() )) ;
    }
    
    public Book create(Book book) {
        return bookRepository.save(book);
    }

}
