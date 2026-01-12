package com.example.BookApplication.Controller;

import com.example.BookApplication.DTO.UserProfileResponse;
import com.example.BookApplication.Entity.User;
import com.example.BookApplication.Respository.UserRepository;
import com.example.BookApplication.Security.SecurityUtil;
import com.example.BookApplication.Service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserRepository userRepository;
    private final UserProfileService userProfileService;

    @GetMapping
    public UserProfileResponse getProfile() {

        String username = SecurityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userProfileService.buildProfile(user);
    }
}
