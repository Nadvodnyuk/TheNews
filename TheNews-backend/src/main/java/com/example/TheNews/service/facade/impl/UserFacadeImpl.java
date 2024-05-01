package com.example.TheNews.service.facade.impl;

import com.example.TheNews.dto.request.DeleteUserDto;
import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.response.SignInResponseDto;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.GlobalExceptionHandler;
import com.example.TheNews.exception.AlreadyExistException;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.UserService;
import com.example.TheNews.service.facade.UserFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Service;

@Service
public class UserFacadeImpl implements UserFacade {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    //С.Регистрация
    public void registerFacade(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String username,
                               @RequestParam String password) throws NotFoundException {
        UserEntity registeredUser = userService.registerUser(firstName, lastName, username, password);
    }

    //С.Вход
    public SignInResponseDto authenticateFacade(@RequestBody SignInDto loginUserDto) throws NotFoundException {

        UserEntity authenticatedUser = userService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        System.out.println("authenticationEnd " + authenticatedUser);
        SignInResponseDto loginResponse = new SignInResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        System.out.println("Вход выполнен " + SecurityContextHolder.getContext().getAuthentication());
        return loginResponse;
    }

    public boolean authenticatedUserFacade() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication " + authentication);
//        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
//        System.out.println("currentUser " + currentUser);
        System.out.println(authentication.getPrincipal());
        if (authentication.getPrincipal()=="anonymousUser") {
            System.out.println("Аноним");
            return false;}
        else
            return true;
    }

    //С.Выйти
    public void logOutFacade(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader("Authorization");
        System.out.println("Вызван логаут");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            jwtService.invalidateToken(token);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public long deleteFacade(DeleteUserDto user) {
        return (userService.delete(user.getUser_id()));
    }
}
