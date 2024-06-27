package com.example.TheNews.service;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.Theme;
import com.example.TheNews.exception.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service

public interface ArticleService {

    List<ArticleEntity> getAllLatestArticles();

    void createArticle(String title, String text, String imageUrl, Set<Theme> topics);

    ArticleEntity getOne(Long art_id) throws NotFoundException;

    void editArticle(long article_id, String title, String text, String imageUrl, Set<Theme> topics);
    void delete(Long art_id);

    void saveArticles(List<ArticleEntity> articles);

    List<ArticleEntity> getArticlesByUserPreferences(Set<Theme> favoriteTopics, Set<Theme> blockedTopics);
}
