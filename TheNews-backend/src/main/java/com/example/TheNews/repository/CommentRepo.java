package com.example.TheNews.repository;

import java.util.List;
import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.entity.ArticleEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByArticleC(ArticleEntity article_id, PageRequest pageRequest);

    List<CommentEntity> findByArticleCOrderByCommentDateDesc(ArticleEntity article_id, PageRequest pageRequest);

    List<CommentEntity> findByArticleC(ArticleEntity article_id);
}