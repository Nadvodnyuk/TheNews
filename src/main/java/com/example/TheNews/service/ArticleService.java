package com.example.TheNews.service;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.model.Article;
import org.springframework.stereotype.Service;

@Service

public interface ArticleService {

    public Article createArticle(ArticleEntity art, Long user_id);
    public Article getOne(Long art_id) throws NotFoundException;
    public Long delete(Long art_id);
}
