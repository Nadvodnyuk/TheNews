package com.example.TheNews.repository;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.LikeEntity;

import java.util.List;

import com.example.TheNews.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LikeRepo extends JpaRepository<LikeEntity, Long> {
    List<LikeEntity> findByArticleL(ArticleEntity article_id);
    List<LikeEntity> findByUserL(UserEntity user_id);

    boolean existsByArticleLAndUserL(ArticleEntity articleL, UserEntity userL);

    @Transactional(rollbackFor = Exception.class)
    void deleteByArticleLAndUserL(ArticleEntity article_id, UserEntity user_id);
}