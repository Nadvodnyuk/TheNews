package com.example.TheNews.service;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.ArticleRepo;
import com.example.TheNews.service.impl.ArticleServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.TheNews.entity.Role.ROLE_USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTests {
    @Mock
    private ArticleRepo articleRepo;

    @InjectMocks
    private ArticleServiceImpl articleService;

    UserEntity user = UserEntity.builder()
            .user_id(1)
            .first_name("Yana")
            .last_name("Mark")
            .role(ROLE_USER)
            .username("Team")
            .password("222")
            .createdAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
            .updatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now())).build();

    ArticleEntity article = ArticleEntity.builder()
            .article_id(1)
            .title("The Article")
            .article_text("This is the article we've created")
            .image_url("image")
            .like_num(2)
            .comment_num(1)
            .publicationDate(java.sql.Timestamp.valueOf(LocalDateTime.now()))
            .userA(user).build();

    @Test
    public void ArticleService_getAllLatestArticles_ReturnsList() {
        List<ArticleEntity> allArticles = Mockito.mock(List.class);
        when(articleRepo.findByPublicationDateAfter(Mockito.any(java.sql.Timestamp.class)))
                .thenReturn(allArticles);

        List<ArticleEntity> articlesLast24Hours = articleService.getAllLatestArticles();

        Assertions.assertThat(articlesLast24Hours).isNotNull();
    }

    @Test
    public void ArticleService_CreateArticle_ReturnsVoid() {

        when(articleRepo.save(Mockito.any(ArticleEntity.class))).thenReturn(article);

        assertAll(() -> articleService.createArticle(article.getTitle(),
                article.getArticle_text(),
                article.getImage_url()));
    }

    @Test
    public void ArticleService_getOne_ReturnsArticle() throws NotFoundException {
        when(articleRepo.findById(article.getArticle_id()))
                .thenReturn(Optional.ofNullable(article));
        ArticleEntity art = articleService.getOne(article.getArticle_id());

        Assertions.assertThat(art).isNotNull();
    }

    @Test
    public void ArticleService_editArticle_ReturnsVoid() {
        when(articleRepo.findById(article.getArticle_id())).thenReturn(Optional.ofNullable(article));
        when(articleRepo.save(article)).thenReturn(article);

        assertAll(() -> articleService.editArticle(article.getArticle_id(),
                "Updated article",
                "This is the updated article",
                "Updated image"));
    }

    @Test
    public void ArticleService_delete_ReturnsVoid() {

        doNothing().when(articleRepo).deleteById(article.getArticle_id());

        assertAll(() -> articleService.delete(article.getArticle_id()));
    }
}
