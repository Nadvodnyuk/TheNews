package com.example.TheNews.repository;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.entity.LikeEntity;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<LikeEntity, Long> {
    List<LikeEntity> findByArticleId(ArticleEntity article_id);
}