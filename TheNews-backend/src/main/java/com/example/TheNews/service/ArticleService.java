package com.example.TheNews.service;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface ArticleService {

    List<ArticleEntity> getAllLatestArticles();

    void createArticle(String title, String text, String imageUrl, String topics);

    ArticleEntity getOne(Long art_id) throws NotFoundException;

    void editArticle(long article_id, String title, String text, String imageUrl, String topics);
    void delete(Long art_id);

    void saveArticles(List<ArticleEntity> articles);
}
