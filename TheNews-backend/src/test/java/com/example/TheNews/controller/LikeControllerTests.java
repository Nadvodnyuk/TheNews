package com.example.TheNews.controller;

import com.example.TheNews.dto.request.LikeDto;
import com.example.TheNews.dto.response.ArticleDto;
import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.repository.LikeRepo;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.LikeService;
import com.example.TheNews.service.facade.LikeFacade;
import com.example.TheNews.service.impl.LikeServiceImpl;
import org.h2.engine.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = LikeController.class)
@AutoConfigureMockMvc(addFilters = false)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class LikeControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LikeFacade likeFacade;
    @MockBean
    private JwtService jwtService; // Создаем заглушку для JwtService

    @Test
    public void LikeController_likeNum_ReturnsInt() throws Exception {
        int article_id = 1;
        int likeNum = 1;
        LikeDto likeDto = LikeDto.builder()
                .userL(1).articleL(1).build();
        when(likeFacade.likeNumFacade(article_id)).thenReturn(likeNum);
        mockMvc.perform(post("/likes/likeNum")
                        .param("article_id", String.valueOf(article_id)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(likeNum)));
    }
}