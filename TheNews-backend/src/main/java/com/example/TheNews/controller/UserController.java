package com.example.TheNews.controller;

import com.example.TheNews.dto.UserDto;
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

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity username,
                                       @RequestBody UserDto userDto) {
        try {
            UserEntity userRequest = modelMapper.map(userDto, UserEntity.class);
            UserDto userResponse = modelMapper.map(
                    userService.registration(username), UserDto.class);
            return ResponseEntity.ok(userResponse);
        } catch (AlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getUserById(@RequestParam Long user_id) {
        try {
            UserDto userResponse = modelMapper.map(userService.getOne(user_id), UserDto.class);
            return ResponseEntity.ok(userResponse);
        } catch (NotFoundException e) {
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
