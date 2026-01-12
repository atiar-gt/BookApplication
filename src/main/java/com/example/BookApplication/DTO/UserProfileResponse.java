package com.example.BookApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserProfileResponse {

    private Long userId;
    private String username;
    private String role;

    private List<BookResponse> favoriteBooks;
    private List<BookResponse> readBooks;
}
