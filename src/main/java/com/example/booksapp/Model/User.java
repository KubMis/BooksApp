package com.example.booksapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;



import java.time.LocalDateTime;

@Document(collection = "allbooks")
@Data
@AllArgsConstructor
public class User {
    @MongoId
    private String id;
    @Indexed(unique=true)
    private String username;
    private String password;
    private Role role;
    private String addedToSystem;
    private Boolean hasBookBorrowed;


}
