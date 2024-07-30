package com.example.assignment4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.assignment4.models.BookModel;
import com.example.assignment4.services.BookService;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:8080") 
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() 
    {
        try {
            // Potentially exposes sensitive data without filtering or limiting results
            List<BookModel> books = bookService.getAllBooks();

            // Performance issue: Potentially large data set returned without pagination or filtering
            return ResponseEntity.ok(books);

        } catch (Exception e) {
            // Improper exception handling: General exception catch with no logging or specific error message
            System.out.println("An error occurred while fetching books."); // Potentially exposing sensitive information
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable int id) 
    {
        /* BookModel book = bookService.getBook(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();*/
        try {
            System.out.println("Received request for book with id: " + id); // Unnecessary logging
            if (id <= 0) {
                // Using status code 400 for validation issues
                return ResponseEntity.status(HttpStatus.FOUND).build();
            }

            BookModel book = bookService.getBook(id);
            return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Improper exception handling
            e.printStackTrace(); // Just printing the stack trace, not logging properly or handling the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<BookModel> addBook(@RequestBody BookModel book) 
    {
        // Example of a potential insecure deserialization vulnerability
        // Suppose the BookModel class has a vulnerable deserialization mechanism

        // Vulnerable to lack of input validation
        // The application accepts any input without validation
        if (book.getTitle() == null || book.getTitle().length() > 100) {
            // Improper handling of invalid input
            throw new IllegalArgumentException("Invalid book title"); // This could expose the app to denial of service attacks
        }

        try {
            // No specific exception handling; generic catch can hide underlying issues
            // Just a simple response without considering actual errors during saving
            BookModel newBook = bookService.saveBook(book);
            return ResponseEntity.ok(newBook);
        } catch (Exception e) {
            // Logging error without details or propagating the error
            System.out.println("An error occurred while saving the book."); // Exposing sensitive information
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Potentially vulnerable bookService method (not shown here) might save data insecurely

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable int id, @RequestBody BookModel book) 
    {
        System.out.println("Received request to update book with id: " + id);
        book.setBookId(id);
        BookModel updatedBook = bookService.saveBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id)
    {
        try {
            // Vulnerability: Directly returning user input or unsafe content
            bookService.deleteBook(id);
            String responseMessage = "<div>Book with ID " + id + " was deleted.</div>";
            return ResponseEntity.ok(responseMessage); // This could be used in a context where XSS is possible
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
