package com.example.booksapp.Controller;

import com.example.booksapp.Model.User;
import com.example.booksapp.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BooksController {

    private final UserService userService;


    @GetMapping("/users")
    public List<User> getALlUsers(){
        return userService.getAllUsers();

    }
    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("users")
    public User editUser(@RequestBody User user){
        return userService.editUserData(user);
    }

    @DeleteMapping("/users/{username}")
    public void  deleteUserByUsername(@PathVariable String username){
        userService.deleteUserByUsername(username);
    }

    @GetMapping("users/books")
    public List<User> getUsersWithBorrowedBooks(){
        return userService.getAllUsersWithBorrowedBooks();
    }
    @GetMapping("users/{username}")
    public User getUserByName(@PathVariable String username){
        return userService.findByUsername(username);
    }

    @GetMapping("/admin") //used for testing purposes
    public String testAdmin(){
        return "Secret message for admin";
    }
    @GetMapping("/t")//used for testing purposes
    public String testUser(){
        return "Test seems to be working";
    }
}
