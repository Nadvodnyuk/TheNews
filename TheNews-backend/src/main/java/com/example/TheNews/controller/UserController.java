package com.example.TheNews.controller;

import com.example.TheNews.dto.request.SignInDto;
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
    
    @PostMapping("/sign_up")
    public ResponseEntity<?> signUp() {}

    ///cretae token???

    @PostMapping("/sign_in")
    public ResponseEntity<SignInDto> signIn() {}

    @PostMapping("/sign_out")
    public ResponseEntity<?> signOut() {}
}
