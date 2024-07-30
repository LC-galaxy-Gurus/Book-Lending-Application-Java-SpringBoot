package com.example.assignment4.controllers;

import com.example.assignment4.models.BookModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import com.example.assignment4.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;


    @Mock
    private BookService bookService;

    @Test
    public void testGetAllBooks_Success() {
        List<BookModel> books = new ArrayList<>();
        when(bookService.getAllBooks()).thenReturn(books);

        ResponseEntity<List<BookModel>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
    }

    @Test
    public void testGetAllBooks_ServiceError() {
        when(bookService.getAllBooks()).thenThrow(new RuntimeException("Service error"));

        ResponseEntity<List<BookModel>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for getBookById()
    @Test
    public void testGetBookById_Success() {
        int id = 1;
        BookModel book = new BookModel();
        when(bookService.getBook(id)).thenReturn(book);

        ResponseEntity<BookModel> response = bookController.getBookById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testGetBookById_NotFound() {
        int id = 1;
        when(bookService.getBook(id)).thenReturn(null);

        ResponseEntity<BookModel> response = bookController.getBookById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetBookById_InvalidId() {
        int id = 0;

        ResponseEntity<BookModel> response = bookController.getBookById(id);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertNull(response.getBody()); // Correct the status code and remove unnecessary assertion
    }

    // Test cases for addBook()
    @Test
    public void testAddBook_Success() {
        BookModel book = new BookModel();
        book.setTitle("Test Book");
        BookModel newBook = new BookModel();
        when(bookService.saveBook(book)).thenReturn(newBook);

        ResponseEntity<BookModel> response = bookController.addBook(book);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newBook, response.getBody());
    }

    @Test
    public void testAddBook_ServiceError() {
        BookModel book = new BookModel();
        when(bookService.saveBook(book)).thenThrow(new RuntimeException("Service error"));

        ResponseEntity<BookModel> response = bookController.addBook(book);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for updateBook()
    // ...

    // Test cases for deleteBook()
    // ...
}
