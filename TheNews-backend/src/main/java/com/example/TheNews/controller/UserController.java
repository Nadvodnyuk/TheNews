package com.example.TheNews.controller;

import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.request.SignUpDto;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.UserService;
import com.example.TheNews.service.facade.UserFacade;
import jakarta.servlet.http.HttpServletRequest;
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
    private UserFacade userFacade;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired


    @PostMapping("/sign_up")
    public ResponseEntity<?> register(@RequestBody SignUpDto regUserDto) {

        return userFacade.registerFacade(regUserDto.getFirst_name(),
                regUserDto.getLast_name(),
                regUserDto.getUsername(),
                regUserDto.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody SignInDto loginUserDto) {

        return userFacade.authenticateFacade(loginUserDto);
    }

    @GetMapping("/me")
    public ResponseEntity<?> authenticatedUser() {

        return userFacade.authenticatedUserFacade();
    }

    @PostMapping("/log_out")
    public ResponseEntity<?> logOut(HttpServletRequest request) {
        return userFacade.logOutFacade(request);
    }


//    @GetMapping("/")
//    public ResponseEntity<List<UserEntity>> allUsers() {
//        List<UserEntity> users = userService.allUsers();
//        return ResponseEntity.ok(users);
//    }
}

