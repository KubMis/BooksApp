package com.example.booksapp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User addUser(User newUser){
        return repository.save(newUser);
    }
}
