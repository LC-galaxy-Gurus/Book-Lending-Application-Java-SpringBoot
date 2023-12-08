package com.example.assignment4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.assignment4.models.BookModel;
import com.example.assignment4.repositories.BookRepository;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookModel> getAllBooks() 
    {
        return bookRepository.findAll();
    }
    
    public BookModel getBook(int id) 
    {
        return bookRepository.findById(id).orElse(null);
    }

    public BookModel saveBook(BookModel book) 
    {
        return bookRepository.save(book);
    }

    public void deleteBook(int id) 
    {
        bookRepository.deleteById(id);
    }
}

