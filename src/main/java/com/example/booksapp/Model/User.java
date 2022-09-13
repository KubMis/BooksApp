package com.example.booksapp;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

@Document
@Component
@Data
public class User {
    @MongoId
    private String id;
    private String username;
    private String password;
    private String role;
    private LocalDateTime addedToSystem;
}
