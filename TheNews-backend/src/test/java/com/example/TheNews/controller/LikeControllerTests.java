package com.example.TheNews.controller;

import com.example.TheNews.dto.request.LikeDto;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.facade.LikeFacade;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    @Autowired
    private ObjectMapper objectMapper;
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
        mockMvc.perform(post("/auth/likes/likeNum")
                        .param("article_id", String.valueOf(article_id)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(likeNum)));
    }

    @Test
    public void LikeController_putLike_ReturnsOk() throws Exception {
        LikeDto likeDto = LikeDto.builder()
                .userL(1).articleL(1).build();
        doNothing().when(likeFacade).putLikeFacade(likeDto);
        mockMvc.perform(post("/likes/postLike")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Лайк успешно поставлен"));
    }

    @Test
    public void LikeController_deleteComment_ReturnString() throws Exception {
        LikeDto likeDto = LikeDto.builder()
                .userL(1).articleL(1).build();
        doNothing().when(likeFacade).deleteLikeFacade(likeDto);

        ResultActions response = mockMvc.perform(delete("/user/likes/1/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}