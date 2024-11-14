package com.springboot.restapi.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.springboot.restapi.Entity.User;
import com.springboot.restapi.Service.UserServiceImp;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
	

    @Autowired
    private UserServiceImp userServiceImp;
    
    

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userServiceImp.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> loginRequest, HttpSession session) {
        User user = userServiceImp.login(loginRequest.get("username"), loginRequest.get("password"));
        if (user != null) {
            session.setAttribute("username", user.getUsername());
        }
        return user;
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userServiceImp.getAllUsers();
    }
    
    // Endpoint to get paginated users
    @GetMapping("/paged") 
    public Page<User> getUsersWithPagination(@RequestParam int page, @RequestParam int size)
    {
    	return userServiceImp.getUsersWithPagination(page, size); 
    }
    
    @GetMapping("/username")
    public String getLoggedInUsername(HttpSession session) 
    { 
    	Object username = session.getAttribute("username"); 
    	return (username != null) ? username.toString() : "Anonymous";
    }
}
