package com.example.assignment4.models;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookModelTest {

    private BookModel book;

    @BeforeEach
    void setUp() {
        book = new BookModel();
        book.setBookId(1);
        book.setTitle("Effective Java");
        book.setAuthorLastName("Bloch");
        book.setAuthorFirstName("Joshua");
        book.setRating(5);
    }

    @Test
    void testGettersAndSetters() {
        // Test getters
        assertThat(book.getBookId()).isEqualTo(1);
        assertThat(book.getTitle()).isEqualTo("Effective Java");
        assertThat(book.getAuthorLastName()).isEqualTo("Bloch");
        assertThat(book.getAuthorFirstName()).isEqualTo("Joshua");
        assertThat(book.getRating()).isEqualTo(5);

        // Test setters
        book.setBookId(2);
        book.setTitle("Java Concurrency in Practice");
        book.setAuthorLastName("Goetz");
        book.setAuthorFirstName("Brian");
        book.setRating(4);

        assertThat(book.getBookId()).isEqualTo(2);
        assertThat(book.getTitle()).isEqualTo("Java Concurrency in Practice");
        assertThat(book.getAuthorLastName()).isEqualTo("Goetz");
        assertThat(book.getAuthorFirstName()).isEqualTo("Brian");
        assertThat(book.getRating()).isEqualTo(4);
    }
}

