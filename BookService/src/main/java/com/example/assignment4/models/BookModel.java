package com.example.assignment4.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BookModel 
{
	//Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    private String title;
    private String authorLastName;
    private String authorFirstName;
    private int rating;
    
    // Getters
    public int getBookId() 
    {
        return bookId;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getAuthorLastName() 
    {
        return authorLastName;
    }

    public String getAuthorFirstName() 
    {
        return authorFirstName;
    }

    public int getRating() 
    {
        return rating;
    }

    // Setters
    public void setBookId(int bookId) 
    {
        this.bookId = bookId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public void setAuthorLastName(String authorLastName) 
    {
        this.authorLastName = authorLastName;
    }

    public void setAuthorFirstName(String authorFirstName) 
    {
        this.authorFirstName = authorFirstName;
    }

    public void setRating(int rating) 
    {
        this.rating = rating;
    }
}
