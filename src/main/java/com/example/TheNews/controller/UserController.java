package com.example.TheNews.controller;

import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.UserAlreadyExistException;
import com.example.TheNews.exception.UserNotFoundException;
import com.example.TheNews.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
// @CrossOrigin("http://localhost:3000")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity username) {
        try {
            userService.registration(username);
            return ResponseEntity.ok("Пользователь успешно сохранен");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getUserById(@RequestParam Long user_id) {
        try {
            return ResponseEntity.ok(userService.getOne(user_id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity deleteUser(@PathVariable Long user_id) {
        try {
            return ResponseEntity.ok(userService.delete(user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
