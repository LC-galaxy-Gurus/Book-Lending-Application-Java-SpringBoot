package com.example.assignment4.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.assignment4.models.BookModel;
import com.example.assignment4.repositories.BookRepository;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBooks_Success() {
        List<BookModel> expectedBooks = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<BookModel> actualBooks = bookService.getAllBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test(expected = RuntimeException.class)
    public void testGetAllBooks_Exception() {
        when(bookRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        bookService.getAllBooks();
    }

    @Test
    public void testGetBook_Found() {
        int id = 1;
        BookModel expectedBook = new BookModel();
        when(bookRepository.findById(id)).thenReturn(Optional.of(expectedBook));

        BookModel actualBook = bookService.getBook(id);

        assertEquals(expectedBook, actualBook);
    }

    @Test
    public void testGetBook_NotFound() {
        int id = 1;
        when(bookRepository.findById(id)).thenReturn(null);

        BookModel actualBook = bookService.getBook(id);

        assertNull(actualBook);
    }

// Test for potential XSS vulnerability requires additional setup for BookModel

    @Test
    public void testSaveBook_Success() {
        BookModel book = new BookModel();
        when(bookRepository.save(book)).thenReturn(book);

        BookModel savedBook = bookService.saveBook(book);

        assertEquals(book, savedBook);
    }

// Tests for input validation, sensitive data handling require modifying this test

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteBook_InvalidId() {
        bookService.deleteBook(0);
    }

    @Test
    public void testDeleteBook_Success() {
        int id = 1;
        doNothing().when(bookRepository).deleteById(id); // Mock void method call

        bookService.deleteBook(id);

        verify(bookRepository, times(1)).deleteById(id);
    }

// Test for exception handling during deletion requires modifying this test
}
