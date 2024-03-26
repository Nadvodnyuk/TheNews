package com.example.TheNews.controller;

import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.AlreadyExistException;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
// @CrossOrigin("http://localhost:3000")

public class UserController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;


    @DeleteMapping("/{user_id}")
    public ResponseEntity deleteUser(@PathVariable Long user_id) {
        try {
            return ResponseEntity.ok(userService.delete(user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
