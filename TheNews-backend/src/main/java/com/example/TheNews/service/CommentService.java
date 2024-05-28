package com.example.TheNews.service;

import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.entity.ArticleEntity;


import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    List<CommentEntity> getCommentsByArticleIdWithPagination(ArticleEntity article_с, int page);

    int getCommentsByArticleId(ArticleEntity article_c);
    void createComment(UserEntity user_c, String comment_text, ArticleEntity article_c);

    void delete(Long comment_id);

}
