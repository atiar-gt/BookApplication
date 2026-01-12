package com.example.BookApplication.Controller;

import com.example.BookApplication.Entity.User;
import com.example.BookApplication.Respository.UserRepository;
import com.example.BookApplication.Security.SecurityUtil;
import com.example.BookApplication.Service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile/books")
public class UserBookController {

    @Autowired
    private UserBookService userBookService;

    @Autowired
    private UserRepository userRepository;

    private User currentUser() {
        String username = SecurityUtil.getCurrentUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping("/{bookId}/read")
    public ResponseEntity<String> read(@PathVariable Long bookId) {
        userBookService.markAsRead(currentUser(), bookId);
        return ResponseEntity.ok("Marked as read");
    }

    @PostMapping("/{bookId}/unread")
    public ResponseEntity<String> unread(@PathVariable Long bookId) {
        userBookService.markAsUnread(currentUser(), bookId);
        return ResponseEntity.ok("Marked as unread");
    }

    @PostMapping("/{bookId}/favorite")
    public ResponseEntity<String> favorite(@PathVariable Long bookId) {
        userBookService.markAsFavorite(currentUser(), bookId);
        return ResponseEntity.ok("Marked as favorite");
    }

    @PostMapping("/{bookId}/unfavorite")
    public ResponseEntity<String> unfavorite(@PathVariable Long bookId) {
        userBookService.unFavorite(currentUser(), bookId);
        return ResponseEntity.ok("Removed from favorites");
    }
}
