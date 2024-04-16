package com.example.TheNews.controller;

import com.example.TheNews.dto.request.LikeDto;
import com.example.TheNews.dto.response.ArticleDto;
import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.repository.LikeRepo;
import com.example.TheNews.service.LikeService;
import com.example.TheNews.service.impl.LikeServiceImpl;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebMvcTest(controllers = LikeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class LikeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LikeService likeService;

    @Autowired
    private ObjectMapper objectMapper;
    private LikeEntity like;
    private LikeDto likeDto;
    private ArticleEntity article;
    private ArticleDto articleDto;


    @BeforeEach
    public void init() {
        like = LikeEntity.builder().name("like").type("electric").build();
        pokemonDto = PokemonDto.builder().name("pickachu").type("electric").build();
        review = Review.builder().title("title").content("content").stars(5).build();
        reviewDto = ReviewDto.builder().title("review title").content("test content").stars(5).build();
    }
    @Test
    public void LikeController_likeNum_ReturnsInt() {
        //сомневаюсь максимально
        List<LikeEntity> likeNum = Mockito.mock(List.class);

        when(likeRepo.findByArticleL(article)).thenReturn(likeNum);

        int saveLike = likeService.getLikesByArticleId(article);

        Assertions.assertThat(saveLike).isNotNull();
    }
}