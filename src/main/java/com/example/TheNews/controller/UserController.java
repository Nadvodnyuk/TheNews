package com.example.TheNews.controller;

import com.example.TheNews.entity.User;
import com.example.TheNews.repository.UserRepo;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin("http://localhost:3000")

public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public List<User> fetchUsers(){
        return userRepo.findAll();
    }
 }
