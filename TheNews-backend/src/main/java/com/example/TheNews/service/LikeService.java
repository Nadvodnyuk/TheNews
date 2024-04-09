package com.example.TheNews.service;

import com.example.TheNews.entity.ArticleEntity;

import com.example.TheNews.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    public int getLikesByArticleId(ArticleEntity article_l);

    public boolean isLikedByUserAndArticle(UserEntity user_l, ArticleEntity article_l);

    public void createLike(UserEntity user_l, ArticleEntity article_l);

    public void delete(UserEntity user_l, ArticleEntity article_l);
}
