package com.example.TheNews.service.facade.impl;

import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.response.SignInResponseDto;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.UserService;
import com.example.TheNews.service.facade.UserFacade;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class UserFacadeImpl implements UserFacade {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    //С.Регистрация
    public ResponseEntity<?> registerFacade(@RequestParam String firstName,
                                            @RequestParam String lastName,
                                            @RequestParam String username,
                                            @RequestParam String password){

        UserEntity registeredUser = userService.registerUser(firstName, lastName, username, password);

        return ResponseEntity.ok(registeredUser);
    }
    //С.Вход
    public ResponseEntity<?> authenticateFacade(@RequestBody SignInDto loginUserDto){
        UserEntity authenticatedUser = userService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        SignInResponseDto loginResponse = new SignInResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    public ResponseEntity<?> authenticatedUserFacade() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity currentUser = (UserEntity) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }
    //С.Выйти
    public ResponseEntity<?> logOutFacade(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            jwtService.invalidateToken(token);
//            SecurityContextHolder.clearContext();
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
