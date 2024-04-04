package com.example.TheNews.service;

import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.exception.NotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public List<CommentEntity> getCommentsByArticleIdWithPagination(long article_с, int page);
    public void createComment(long user_c, String comment_text, long article_c);
    public Long delete(Long comment_id);

}
