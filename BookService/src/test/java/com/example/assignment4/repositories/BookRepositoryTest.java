package com.example.assignment4.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.assignment4.models.BookModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private BookModel book;

    @BeforeEach
    void setUp() {
        book = new BookModel();
        book.setBookId(1);
        book.setTitle("Effective Java");
        book.setAuthorFirstName("Joshua");
        book.setAuthorLastName("Bloch");
        book.setRating(5);

        bookRepository.save(book);
    }

    @Test
    void testFindById() {
        // Given
        int id = book.getBookId();
        // When
        Optional<BookModel> result = bookRepository.findById(id);
        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Effective Java");
    }

    @Test
    void testFindAll() {
        // When
        Iterable<BookModel> books = bookRepository.findAll();
        // Then
        assertThat(books).isNotEmpty();
        assertThat(books).hasSize(1);
        assertThat(books.iterator().next().getTitle()).isEqualTo("Effective Java");
    }

    @Test
    void testSave() {
        // Given
        BookModel newBook = new BookModel();
        newBook.setBookId(1);
        newBook.setTitle("Java Concurrency in Practice");
        newBook.setAuthorFirstName("Brian");
        newBook.setAuthorLastName("Goetz");
        newBook.setRating(4);
        // When
        BookModel savedBook = bookRepository.save(newBook);

        // Then
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Java Concurrency in Practice");
    }

    @Test
    void testDeleteById() {
        // Given
        int id = book.getBookId();
        // When
        bookRepository.deleteById(id);
        // Then
        Optional<BookModel> result = bookRepository.findById(id);
        assertThat(result).isNotPresent();
    }
}

