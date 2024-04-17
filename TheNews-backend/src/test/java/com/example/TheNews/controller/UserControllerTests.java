package com.example.TheNews.controller;

import com.example.TheNews.dto.request.DeleteUserDto;
import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.request.SignUpDto;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.UserService;
import com.example.TheNews.service.facade.UserFacade;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserFacade userFacade;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService; // Создаем заглушку для JwtService

    @Test
    public void UserController_register_ReturnString() throws Exception {
        SignUpDto regUserDto = SignUpDto.builder()
                .first_name("Yana")
                .last_name("Mark")
                .username("Team")
                .password("222")
                .build();
        doNothing().when(userFacade).registerFacade(regUserDto.getFirst_name(),
                regUserDto.getLast_name(),
                regUserDto.getUsername(),
                regUserDto.getPassword());
        mockMvc.perform(post("/users/sign_up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regUserDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Пользователь успешно зарегистрирован"));
    }

    @Test
    public void UserController_authenticate_ReturnString() throws Exception {
        SignInDto loginUserDto = SignInDto.builder()
                .username("Team")
                .password("222")
                .build();
        doNothing().when(userFacade).authenticateFacade(loginUserDto);
        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUserDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Пользователь успешно аутентифицирован"));
    }

    @Test
    public void UserController_authenticatedUser_ReturnString() throws Exception {
        doNothing().when(userFacade).authenticatedUserFacade();
        mockMvc.perform(get("/users/me")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Пользователь успешно авторизирован"));
    }

    @Test
    public void UserController_logOut_ReturnsString() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        doNothing().when(userFacade).logOutFacade(request);
        mockMvc.perform(post("/users/log_out")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Пользователь успешно вышел из аккаунта"));
    }

    @Test
    public void UserController_deleteUser_ReturnString() throws Exception {
        long user_id = 1;
        DeleteUserDto user = new DeleteUserDto();
        user.setUser_id(user_id);
        when(userFacade.deleteFacade(user)).thenReturn(user_id);
        mockMvc.perform(delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
