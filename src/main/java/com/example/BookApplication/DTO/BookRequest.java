package com.example.BookApplication.DTO;

import lombok.Data;

@Data
public class BookRequest {

    private String title;
    private String author;
    private String genre;

    private String imageUrl;

    private String goodreadsId;
    private Double goodreadsRating;
}
