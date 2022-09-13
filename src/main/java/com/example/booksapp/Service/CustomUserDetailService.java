package com.example.booksapp.Service;

import com.example.booksapp.Model.CustomUserDetails;
import com.example.booksapp.Model.User;
import com.example.booksapp.Repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {


    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= repository.findByUsername(username);

        return new CustomUserDetails(user);
    }
}
