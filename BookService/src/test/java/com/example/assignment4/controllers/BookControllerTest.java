package com.example.assignment4.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.assignment4.models.BookModel;
import com.example.assignment4.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Autowired
    private ObjectMapper objectMapper;


    private BookModel book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        book = new BookModel();
        book.setBookId(1);
        book.setTitle("Effective Java");
        book.setAuthorFirstName("Joshua");
        book.setAuthorLastName("Bloch");
        book.setRating(1);
    }

    @Test
    void testGetAllBooks() throws Exception {
        // Given
        when(bookService.getAllBooks()).thenReturn(List.of(book));

        // When
        ResultActions result = mockMvc.perform(get("/books"));

        // Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookId").value(1))
                .andExpect(jsonPath("$[0].title").value("Effective Java"))
                .andExpect(jsonPath("$[0].authorFirstName").value("Joshua"))
                .andExpect(jsonPath("$[0].authorLastName").value("Bloch"))
                .andExpect(jsonPath("$[0].rating").value(1));
    }

    @Test
    void testGetBookById() throws Exception {
        // Given
        when(bookService.getBook(anyInt())).thenReturn(book);

        // When
        ResultActions result = mockMvc.perform(get("/books/1"));

        // Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.authorFirstName").value("Joshua"))
                .andExpect(jsonPath("$.authorLastName").value("Bloch"))
                .andExpect(jsonPath("$.rating").value(1));
    }

    @Test
    void testGetBookByIdNotFound() throws Exception {
        // Given
        when(bookService.getBook(anyInt())).thenReturn(null);

        // When
        ResultActions result = mockMvc.perform(get("/books/999"));

        // Then
        result.andExpect(status().isNotFound());
    }

    @Test
    void testAddBook() throws Exception {
        // Given
        when(bookService.saveBook(any(BookModel.class))).thenReturn(book);

        // When
        ResultActions result = mockMvc.perform(post("/books/addBook")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)));

        // Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.authorFirstName").value("Joshua"))
                .andExpect(jsonPath("$.authorLastName").value("Bloch"))
                .andExpect(jsonPath("$.rating").value(1));
    }

    @Test
    void testUpdateBook() throws Exception {
        // Given
        when(bookService.saveBook(any(BookModel.class))).thenReturn(book);

        // When
        ResultActions result = mockMvc.perform(put("/books/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)));

        // Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.authorFirstName").value("Joshua"))
                .andExpect(jsonPath("$.authorLastName").value("Bloch"))
                .andExpect(jsonPath("$.rating").value(1));
    }

    @Test
    void testDeleteBook() throws Exception {
        // When
        ResultActions result = mockMvc.perform(delete("/books/1"));

        // Then
        result.andExpect(status().isOk());
        verify(bookService).deleteBook(1);
    }
}

