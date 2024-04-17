package com.example.TheNews.facade;

import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.UserRepo;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.UserService;
import com.example.TheNews.service.facade.impl.UserFacadeImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserFacadeTests {

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserFacadeImpl userFacade;

    @Mock
    private UserRepo userRepo;

    @Mock
    private AuthenticationManager authenticationManager;

    UserEntity user = UserEntity.builder()
            .user_id(1)
            .first_name("Yana")
            .last_name("Mark")
            .role("USER")
            .username("Team")
            .password("222")
            .createdAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
            .updatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now())).build();

    @Test
    public void testAuthenticateFacade() throws NotFoundException {
        // Создание объекта UserEntity, который будет возвращен методом userService.authenticate
        UserEntity authenticatedUser = new UserEntity();
        when(userService.authenticate(any(SignInDto.class))).thenReturn(authenticatedUser);

        // Создание JWT токена
        String jwtToken = "your_generated_jwt_token";

        // Установка заглушки для возвращаемого значения от jwtService.generateToken
        when(jwtService.generateToken(any(UserEntity.class))).thenReturn(jwtToken);

        // Установка заглушки для возвращаемого значения от jwtService.getExpirationTime
        long time = 3600L;
        when(jwtService.getExpirationTime()).thenReturn(time);

        // Вызов метода вашего фасада, который вы хотите протестировать
        userFacade.authenticateFacade(new SignInDto());

        // Проверка вызова метода userService.authenticate с правильным аргументом
        verify(userService).authenticate(any(SignInDto.class));

        // Проверка вызова метода jwtService.generateToken с правильным аргументом
        verify(jwtService).generateToken(any(UserEntity.class));

        // Проверка вызова метода jwtService.getExpirationTime
        verify(jwtService).getExpirationTime();
    }

    public void UserFacade_authenticatedUserFacade_ReturnsVoid() {

    }

    //С.Выйти
    public void UserFacade_logOutFacade_ReturnsVoid() {

    }
}
