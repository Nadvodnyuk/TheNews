package com.example.TheNews.controller;

import com.example.TheNews.dto.request.CreateArticleDto;
import com.example.TheNews.dto.request.EditArticleDto;
import com.example.TheNews.dto.response.ArticleDto;
import com.example.TheNews.service.ArticleService;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.facade.ArticleFacade;
import com.fasterxml.jackson.databind.ObjectMapper;

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

@WebMvcTest(controllers = ArticleController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ArticleControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ArticleFacade articleFacade;

    @MockBean
    private ArticleService articleService;

    @MockBean
    private JwtService jwtService; // Создаем заглушку для JwtService

    @Test
    public void ArticleController_getAllArticles_ReturnsList() throws Exception {
        ArticleDto articleDto = ArticleDto.builder()
                .article_id(1)
                .title("Article")
                .article_text("Text")
                .image_url("Image")
                .like_num(3)
                .comment_num(15).build();

        when(articleFacade.getAllLatestArticlesFacade()).thenReturn(Arrays.asList(articleDto));
        mockMvc.perform(get("/articles/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                        CoreMatchers.is(Arrays.asList(articleDto).size())));
    }

    @Test
    public void ArticleController_createArticle_ReturnsOk() throws Exception {
        CreateArticleDto createArticleDto = CreateArticleDto.builder()
                .title("Article")
                .article_text("Text")
                .image_url("Image").build();

        doNothing().when(articleFacade).createArticleFacade(createArticleDto);
        mockMvc.perform(post("/articles/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createArticleDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Статья успешно сохранена"));
    }

    @Test
    public void ArticleController_getArticleById_ReturnsArticleDto() throws Exception {
        long article_id = 1;
        ArticleDto articleDto = ArticleDto.builder()
                .article_id(article_id)
                .title("Article")
                .article_text("Text")
                .image_url("Image")
                .like_num(3)
                .comment_num(15).build();

        when(articleFacade.getOneFacade(article_id)).thenReturn(articleDto);
        mockMvc.perform(get("/articles/article/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                        CoreMatchers.is(articleDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.article_id",
                        CoreMatchers.is((int) articleDto.getArticle_id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.article_text",
                        CoreMatchers.is(articleDto.getArticle_text())));
    }

    @Test
    public void ArticleController_updateArticle_ReturnsOk() throws Exception {
        EditArticleDto editArticleDto = EditArticleDto.builder()
                .article_id(1)
                .title("Article")
                .article_text("Text")
                .image_url("Image").build();

        doNothing().when(articleFacade).editArticleFacade(editArticleDto);
        mockMvc.perform(put("/articles/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editArticleDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Статья успешно обновлена"));
    }

    @Test
    public void ArticleController_deleteArticle_ReturnString() throws Exception {
        long article_id = 1;
        doNothing().when(articleFacade).deleteFacade(article_id);

        ResultActions response = mockMvc.perform(delete("/articles/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
