package com.example.booksapp;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BooksController {

    private final UserService userService;

    @GetMapping("/t")
    public String testapi(String t){
        return "Test seems to be working";
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){

        return userService.addUser(user);
    }
}
