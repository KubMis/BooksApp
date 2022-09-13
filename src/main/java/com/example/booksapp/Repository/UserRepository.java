package com.example.booksapp.Repository;

import com.example.booksapp.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    User findByUsername(String username);
}
