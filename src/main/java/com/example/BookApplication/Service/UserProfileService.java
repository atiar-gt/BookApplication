package com.example.BookApplication.Service;

import com.example.BookApplication.DTO.BookResponse;
import com.example.BookApplication.DTO.UserProfileResponse;
import com.example.BookApplication.Entity.User;
import com.example.BookApplication.Entity.UserBook;
import com.example.BookApplication.Respository.UserBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserBookRepository userBookRepository;

    public UserProfileResponse buildProfile(User user) {

        List<BookResponse> favoriteBooks =
                userBookRepository.findByUserAndFavoriteTrue(user)
                        .stream()
                        .map(this::toBookResponse)
                        .toList();

        List<BookResponse> readBooks =
                userBookRepository.findByUserAndReadTrue(user)
                        .stream()
                        .map(this::toBookResponse)
                        .toList();

        return new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                favoriteBooks,
                readBooks
        );
    }

    private BookResponse toBookResponse(UserBook userBook) {
        var book = userBook.getBook();
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getImageUrl(),
                book.getGoodreadsRating()
        );
    }
}
