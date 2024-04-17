package com.example.TheNews.service;

import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.UserRepo;
import com.example.TheNews.service.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

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
    public void UserService_registerUser_ReturnsUser() {
        String encodedPassword = "";
        when(passwordEncoder.encode(Mockito.any(String.class))).thenReturn(encodedPassword);
        when(userRepo.save(Mockito.any(UserEntity.class))).thenReturn(user);

        UserEntity newUser = userService.registerUser("first name",
                "last name",
                "user2",
                "pass");

        Assertions.assertThat(newUser).isNotNull();
    }

    @Test
    public void UserService_authenticate_ReturnsUser() {
        SignInDto dto = new SignInDto("Team", "222");

        when(userRepo.findByUsername(dto.getUsername())).thenReturn(Optional.ofNullable(user));

        UserEntity newUser = userService.authenticate(dto);

        Assertions.assertThat(newUser).isNotNull();
        System.out.println(newUser);
    }

    @Test
    public void UserService_allUsers_ReturnsList() {
        List<UserEntity> allUsers = Mockito.mock(List.class);
        when(userRepo.findAll()).thenReturn(allUsers);

        List<UserEntity> users = userService.allUsers();

        Assertions.assertThat(users).isNotNull();
    }

    @Test
    public void UserService_getOne_ReturnsArticle() throws NotFoundException {
        when(userRepo.findById(user.getUser_id()))
                .thenReturn(Optional.ofNullable(user));
        UserEntity newUser = userService.getOne(user.getUser_id());

        Assertions.assertThat(newUser).isNotNull();
    }

    @Test
    public void UserService_getOne_ReturnsUser() throws NotFoundException {
        when(userRepo.findById(user.getUser_id()))
                .thenReturn(Optional.ofNullable(user));
        UserEntity art = userService.getOne(user.getUser_id());

        Assertions.assertThat(art).isNotNull();
    }


    @Test
    public void UserService_delete_ReturnsLong() {
        doNothing().when(userRepo).deleteById(user.getUser_id());

        Long newUser = userService.delete(user.getUser_id());

        Assertions.assertThat(newUser).isNotNull();
    }
}