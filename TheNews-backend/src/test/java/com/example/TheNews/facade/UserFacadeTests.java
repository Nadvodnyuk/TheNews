package com.example.TheNews.facade;

import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.response.SignInResponseDto;
import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.UserRepo;
import com.example.TheNews.service.facade.impl.UserFacadeImpl;
import com.example.TheNews.service.impl.JwtServiceImpl;
import com.example.TheNews.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserFacadeTests {

    @InjectMocks
    private UserServiceImpl userService;
    @InjectMocks
    private UserFacadeImpl userFacade;

    @InjectMocks
    private JwtServiceImpl jwtService;

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
    public void UserFacade_authenticateFacade_ReturnsVoid() throws NotFoundException {
        SignInDto loginUserDto = new SignInDto("Team", "222");
        when(userRepo.findByUsername(loginUserDto.getUsername())).thenReturn(Optional.of(user));
        UserEntity authenticatedUser = userService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        System.out.println(jwtToken);

        long time = 3600L;

        System.out.println(authenticatedUser);

        when(jwtService.generateToken(authenticatedUser)).thenReturn(jwtToken);
        when(jwtService.getExpirationTime()).thenReturn(time);

        assertAll(() -> userFacade.authenticateFacade(loginUserDto));
    }

    public void UserFacade_authenticatedUserFacade_ReturnsVoid() {

    }

    //С.Выйти
    public void UserFacade_logOutFacade_ReturnsVoid() {

    }
}