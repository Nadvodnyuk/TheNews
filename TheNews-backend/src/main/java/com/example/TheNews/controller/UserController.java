package com.example.TheNews.controller;

import com.example.TheNews.dto.request.DeleteUserDto;
import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.request.SignUpDto;
import com.example.TheNews.service.facade.UserFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    //ок
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

    //ок
    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticate(@RequestBody SignInDto loginUserDto) {
        try {
            return ResponseEntity.ok(userFacade.authenticateFacade(loginUserDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    //ок
    @GetMapping("/auth/users/me")
    public ResponseEntity<?> authenticatedUser() {

        try {
            return ResponseEntity.ok(userFacade.authenticatedUserFacade());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    //ок
    @PostMapping("/both/users/log_out")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> logOut(HttpServletRequest request, HttpServletResponse response) {
        try {
            userFacade.logOutFacade(request, response);
            return ResponseEntity.ok("Пользователь успешно вышел из аккаунта");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

