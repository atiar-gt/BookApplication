package com.example.BookApplication.Respository;

import com.example.BookApplication.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Integer> {
    public Book findByTitle(String title);
}
