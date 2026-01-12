package com.example.BookApplication.Controller;

import com.example.BookApplication.DTO.BookRequest;
import com.example.BookApplication.Entity.Book;
import com.example.BookApplication.Entity.User;
import com.example.BookApplication.Respository.UserRepository;
import com.example.BookApplication.Security.SecurityUtil;
import com.example.BookApplication.Service.BookService;
import com.example.BookApplication.Service.CurrentUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book/v1")
public class BookController {

    public final BookService bookService;
    private final UserRepository userRepository;

    @Autowired
    public BookController(BookService bookService, UserRepository userRepository, CurrentUserService currentUserService) {
        this.bookService = bookService;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
    }

    private User getCurrentUser() {
        String username = SecurityUtil.getCurrentUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    private final CurrentUserService currentUserService;

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody BookRequest request) {

        User user = currentUserService.get();

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setGenre(request.getGenre());
        book.setImageUrl(request.getImageUrl());
//        book.setGoodreadsId(request.getGoodreadsId());
        book.setGoodreadsRating(request.getGoodreadsRating());
        book.setCreatedBy(user);

        return ResponseEntity.ok(bookService.addBook(book));
    }


    @GetMapping("/getBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/getBook/{bookName}")
    public ResponseEntity<Book> getBookByName(@PathVariable String bookName) {
        Book book = bookService.getBookByName(bookName);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


}
