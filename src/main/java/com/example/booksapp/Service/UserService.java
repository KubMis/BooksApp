package com.example.booksapp.Service;
import com.example.booksapp.Model.User;
import com.example.booksapp.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public User addUser(User newUser){
        newUser.setAddedToSystem(LocalDateTime.now());
        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        return repository.save(newUser);
    }

        public List<User> getAllUsers () {
            return repository.findAll();
        }
    }

