package com.example.assignment4.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.assignment4.models.BookModel;
import com.example.assignment4.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private BookModel book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        book = new BookModel();
        book.setBookId(1);
        book.setTitle("Effective Java");
        book.setAuthorLastName("Bloch");
        book.setAuthorFirstName("Joshua");
        book.setRating(5);
    }

    @Test
    void testGetAllBooks() {
        // Given
        List<BookModel> books = Collections.singletonList(book);
        when(bookRepository.findAll()).thenReturn(books);

        // When
        List<BookModel> result = bookService.getAllBooks();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Effective Java");
    }

    @Test
    void testGetBook() {
        // Given
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));

        // When
        BookModel result = bookService.getBook(1);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Effective Java");
    }

    @Test
    void testGetBookNotFound() {
        // Given
        when(bookRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When
        BookModel result = bookService.getBook(999);

        // Then
        assertThat(result).isNull();
    }

    @Test
    void testSaveBook() {
        // Given
        when(bookRepository.save(any(BookModel.class))).thenReturn(book);

        // When
        BookModel result = bookService.saveBook(book);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Effective Java");
    }

    @Test
    void testDeleteBook() {
        // When
        bookService.deleteBook(1);

        // Then
        verify(bookRepository, times(1)).deleteById(1);
    }
}

