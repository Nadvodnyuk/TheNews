package com.example.TheNews.controller;

import com.example.TheNews.dto.request.CreateCommentDto;
import com.example.TheNews.service.CommentService;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.facade.CommentFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.TheNews.dto.response.CommentDto;

import java.time.LocalDateTime;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CommentControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CommentFacade commentFacade;

    @MockBean
    private CommentService commentService;

    @MockBean
    private JwtService jwtService; // Создаем заглушку для JwtService

    @Test
    public void CommentController_getCommentsForArticle_ReturnsList() throws Exception {
        int article_id = 1;
        int page = 1;
        CommentDto commentDto = CommentDto.builder()
                .comment_id(1)
                .comment_text("comment")
                .comment_date(java.sql.Timestamp.valueOf(LocalDateTime.now())).build();

        when(commentFacade.getCommentsByArticleIdWithPaginationFacade(article_id, page))
                .thenReturn(Arrays.asList(commentDto));
        mockMvc.perform(get("/auth/comments/showComments/{article_id}/{page}", article_id, page))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                        CoreMatchers.is(Arrays.asList(commentDto).size())));
    }

    @Test
    public void CommentController_postComment_ReturnsOk() throws Exception {
        CreateCommentDto createCommentDto = CreateCommentDto.builder()
                .comment_text("comment").build();
        int user_id = 1;
        int article_id = 1;
        doNothing().when(commentFacade).createCommentFacade(user_id, createCommentDto, article_id);
        mockMvc.perform(post("/user/comments/postComment")
                        .param("user_id", String.valueOf(user_id))
                        .param("article_id", String.valueOf(article_id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCommentDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Комментарий успешно сохранен"));
    }

    @Test
    public void CommentController_deleteComment_ReturnString() throws Exception {
        int comment_id = 1;
        doNothing().when(commentFacade).deleteFacade(comment_id);

        ResultActions response = mockMvc.perform(delete("/admin/comments/{comment_id}", comment_id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
