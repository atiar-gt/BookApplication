package com.example.BookApplication.Service;

import com.example.BookApplication.Entity.Book;
import com.example.BookApplication.Respository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByName(String name){
        return bookRepository.findByTitle(name);
    }

    public Book updateBook(Long id, Book updatedBook) {

        Book existingBook = bookRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {

        if (!bookRepository.existsById(Math.toIntExact(id))) {
            throw new RuntimeException("Book not found");
        }

        bookRepository.deleteById(Math.toIntExact(id));
    }

}

