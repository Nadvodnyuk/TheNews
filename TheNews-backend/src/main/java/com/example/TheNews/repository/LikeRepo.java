package com.example.TheNews.repository;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.LikeEntity;

import java.util.List;

import com.example.TheNews.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<LikeEntity, Long> {
    List<LikeEntity> findByArticleL(ArticleEntity article_id);

    List<LikeEntity> findByArticleLAndUserL(ArticleEntity article_id, UserEntity user_id);

    void deleteByArticleLAndUserL(ArticleEntity article_id, UserEntity user_id);
}