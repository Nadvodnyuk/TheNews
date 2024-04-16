package com.example.TheNews.facade;

import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.response.SignInResponseDto;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserFacadeTests {
    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private JwtServiceImpl jwtService;


    public void UserFacade_authenticateFacade_ReturnsVoid() throws NotFoundException {

    }

    public void UserFacade_authenticatedUserFacade_ReturnsVoid() {

    }

    //С.Выйти
    public void UserFacade_logOutFacade_ReturnsVoid() {

    }
}
