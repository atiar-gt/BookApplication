package com.example.BookApplication.Respository;

import com.example.BookApplication.Entity.Book;
import com.example.BookApplication.Entity.User;
import com.example.BookApplication.Entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {

    Optional<UserBook> findByUserAndBook(User user, Book book);

    List<UserBook> findByUserAndReadTrue(User user);

    List<UserBook> findByUserAndFavoriteTrue(User user);
}
