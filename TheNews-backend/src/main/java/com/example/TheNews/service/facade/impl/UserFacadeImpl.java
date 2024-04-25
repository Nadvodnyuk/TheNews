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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void authenticateFacade(@RequestBody SignInDto loginUserDto) throws NotFoundException {

        UserEntity authenticatedUser = userService.authenticate(loginUserDto);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());
//
//        // Устанавливаем аутентификацию в контекст безопасности
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("!!!" + SecurityContextHolder.getContext().getAuthentication());
        String jwtToken = jwtService.generateToken(authenticatedUser);

        SignInResponseDto loginResponse = new SignInResponseDto();
        loginResponse.setToken(jwtToken);
        System.out.println("jwtToken!!!" + jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        //SecurityContextHolder.getContext().getAuthentication().setAuthenticated(true);
    }

    public void authenticatedUserFacade() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication " + authentication);
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        System.out.println("currentUser " + currentUser);
    }

    //С.Выйти
    public void logOutFacade(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            jwtService.invalidateToken(token);

        }
        SecurityContextHolder.clearContext();
    }

    public long deleteFacade(DeleteUserDto user) {
        return (userService.delete(user.getUser_id()));
    }
}
