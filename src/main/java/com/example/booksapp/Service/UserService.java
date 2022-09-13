package com.example.booksapp.Service;

import com.example.booksapp.Model.User;
import com.example.booksapp.Repository.UserRepository;
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
