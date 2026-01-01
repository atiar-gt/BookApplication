package com.example.BookApplication.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")   // 👈 MUST be exactly this
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;
}
