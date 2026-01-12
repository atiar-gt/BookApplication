package com.example.BookApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private String genre;

    private String imageUrl;

    private Double goodreadsRating;
}

