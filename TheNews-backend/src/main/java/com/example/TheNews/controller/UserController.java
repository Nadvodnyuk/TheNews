package com.example.TheNews.controller;

import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.response.SignInResponseDto;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
// @CrossOrigin("http://localhost:3000")

public class UserController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    
//    @PostMapping("/sign_up")
//    public ResponseEntity<?> signUp() {}
//
//    ///create token???
//
//    @PostMapping("/sign_in")
//    public ResponseEntity<SignInDto> signIn() {}


//    НУЖНО ЭТО СДЕЛАТЬ Я НЕ ЗНАЮ КАК
//    @PostMapping("/sign_out")
//    public ResponseEntity<?> signOut() {}

    public UserController(JwtService jwtService, UserService authenticationService) {
        this.jwtService = jwtService;
        this.userService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> register(@RequestParam String firstName,
                                               @RequestParam String lastName,
                                               @RequestParam String username,
                                               @RequestParam String password) {
        UserEntity registeredUser = userService.registerUser(firstName, lastName, username, password);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponseDto> authenticate(@RequestBody SignInDto loginUserDto) {
        UserEntity authenticatedUser = userService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        SignInResponseDto loginResponse = new SignInResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());


        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserEntity> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity currentUser = (UserEntity) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/users/")
    public ResponseEntity<List<UserEntity>> allUsers() {
        List<UserEntity> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}

