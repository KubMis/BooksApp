package com.example.booksapp.Service;
import com.example.booksapp.Model.User;
import com.example.booksapp.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User addUser(User newUser){
        newUser.setAddedToSystem(LocalDateTime.now().toString());
        newUser.setRole(newUser.getRole());
        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        return repository.save(newUser);
    }

        public List<User> getAllUsers () {
            return repository.findAll();
        }


        public List<User> getAllUsersWithBorrowedBooks(){
        return repository.findAll().stream()
                            .filter(g-> g.getHasBookBorrowed())
                            .collect(Collectors.toList());
    }

        public User findByUsername(String username){
        return repository.findByUsername(username);
        }

        public User editUserData(User user){
           User editedUser= repository.findByUsername(user.getUsername());
           editedUser.setUsername(user.getUsername());
           editedUser.setPassword(user.getPassword());
           editedUser.setHasBookBorrowed(user.getHasBookBorrowed());
           return editedUser;
        }
        public void deleteUserByUsername(String username){
        repository.deleteByUsername(username);
        }


    }

