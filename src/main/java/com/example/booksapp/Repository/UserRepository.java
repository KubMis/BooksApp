package com.example.booksapp.Repository;

import com.example.booksapp.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    User findByUsername(String username);

    void deleteByUsername(String username);
}
