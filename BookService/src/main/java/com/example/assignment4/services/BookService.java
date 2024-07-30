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
       try {
           // Performance issue: Fetching all books without pagination or limits
           // This can lead to performance problems with large datasets
           return bookRepository.findAll();
       } catch(Exception e) {
           // Improper exception handling: Generic catch with no logging or specific error message
           // This might expose sensitive information or make debugging difficult
           System.out.println("An error occurred while fetching books."); // Exposing sensitive details
           // Optionally rethrow or handle exception in a way that doesn't help security
           throw new RuntimeException("Failed to fetch books"); // Generic runtime exception
       }
    }
    
    public BookModel getBook(int id) 
    {
        BookModel book = bookRepository.findById(id).orElse(null);

        // Simulate exposure of unvalidated user input or data
        // Suppose BookModel has a method to directly output data
        if (book != null) {
            // Introducing a scenario where book details might be rendered as HTML
            // Vulnerability: directly exposing data without proper escaping
            String unsafeContent = book.getTitle(); // Assume this content could be unsafe HTML
            book.setTitle(unsafeContent);
        }
        return book;
    }

    public BookModel saveBook(BookModel book) 
    {
        // Vulnerability: No input validation or sanitization
        // The method saves the book directly without checking or sanitizing input

        // Simulating improper handling of sensitive fields
        // Suppose BookModel has a sensitive field like 'internalNotes'
        if (book.getAuthorFirstName() != null) {
            // Exposing sensitive information
            System.out.println("Saving book of Author " + book.getAuthorFirstName()); // Potential information leak
        }

        // No exception handling - could expose or mishandle errors
        // Example: If bookRepository.save throws an unchecked exception, it will propagate
        return bookRepository.save(book);
    }

    public void deleteBook(int id) 
    {
        // Vulnerability: No validation or authorization checks
        // If `id` is not validated, it might lead to unintended deletions
        if (id <= 0) {
            // Improper handling of invalid ID; might lead to unexpected behavior
            throw new IllegalArgumentException("Invalid ID provided"); // Could expose internal logic or allow bypassing
        }

        // Vulnerability: No exception handling; potential issues if deletion fails
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            // Logging sensitive details improperly
            System.out.println("Error deleting book with ID: " + id); // Exposing internal details
            // No specific error handling or rethrowing the exception
            // This could lead to incomplete or failed operations without proper feedback
        }
    }
}

