package com.example.BookApplication.Service;

import com.example.BookApplication.Entity.Book;
import com.example.BookApplication.Entity.User;
import com.example.BookApplication.Entity.UserBook;
import com.example.BookApplication.Respository.BookRepository;
import com.example.BookApplication.Respository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserBookService {

    @Autowired
    private UserBookRepository userBookRepository;

    @Autowired
    private BookRepository bookRepository;

    private UserBook getOrCreate(User user, Long bookId) {
        Book book = bookRepository.findById(Math.toIntExact(bookId))
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return userBookRepository
                .findByUserAndBook(user, book)
                .orElseGet(() -> {
                    UserBook ub = new UserBook();
                    ub.setUser(user);
                    ub.setBook(book);
                    ub.setRead(false);
                    ub.setFavorite(false);
                    ub.setMarkedAt(LocalDateTime.now());
                    return ub;
                });
    }

    public void markAsRead(User user, Long bookId) {
        UserBook ub = getOrCreate(user, bookId);
        ub.setRead(true);
        userBookRepository.save(ub);
    }

    public void markAsUnread(User user, Long bookId) {
        UserBook ub = getOrCreate(user, bookId);
        ub.setRead(false);
        userBookRepository.save(ub);
    }

    public void markAsFavorite(User user, Long bookId) {
        UserBook ub = getOrCreate(user, bookId);
        ub.setFavorite(true);
        userBookRepository.save(ub);
    }

    public void unFavorite(User user, Long bookId) {
        UserBook ub = getOrCreate(user, bookId);
        ub.setFavorite(false);
        userBookRepository.save(ub);
    }
}

