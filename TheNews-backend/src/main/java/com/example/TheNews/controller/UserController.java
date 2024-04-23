package com.example.TheNews.controller;

import com.example.TheNews.dto.request.DeleteUserDto;
import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.request.SignUpDto;
import com.example.TheNews.exception.AlreadyExistException;
import com.example.TheNews.service.facade.UserFacade;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")

public class UserController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserFacade userFacade;

    @PostMapping("/auth/sign_up")
    public ResponseEntity<?> register(@RequestBody SignUpDto regUserDto) {
        try {
            userFacade.registerFacade(regUserDto.getFirst_name(),
                    regUserDto.getLast_name(),
                    regUserDto.getUsername(),
                    regUserDto.getPassword());
            return ResponseEntity.ok("Пользователь успешно зарегистрирован");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticate(@RequestBody SignInDto loginUserDto) {
        try {
            userFacade.authenticateFacade(loginUserDto);
            return ResponseEntity.ok("Пользователь успешно аутентифицирован");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/both/users/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> authenticatedUser() {

        try {
            userFacade.authenticatedUserFacade();
            return ResponseEntity.ok("Пользователь успешно авторизирован");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/both/users/log_out")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> logOut(HttpServletRequest request) {
        try {
            System.out.println(request);
            userFacade.logOutFacade(request);
            return ResponseEntity.ok("Пользователь успешно вышел из аккаунта");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/admin/users/{user_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable long user_id) {
        try {
            DeleteUserDto user = new DeleteUserDto();
            user.setUser_id(user_id);
            return ResponseEntity.ok(userFacade.deleteFacade(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}

