package com.springboot.restapi.Service;

import com.springboot.restapi.Entity.*;
import com.springboot.restapi.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    // Remove PasswordEncoder
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    public User register(User user) {
        // Directly save the password without encoding
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        // Compare plain text passwords for now
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllByUsername();
    }
    
 // Method to get users with pagination 
    public Page<User> getUsersWithPagination(int page, int size)
    { 
    	return userRepository.findAll(PageRequest.of(page, size)); 
    }
}
