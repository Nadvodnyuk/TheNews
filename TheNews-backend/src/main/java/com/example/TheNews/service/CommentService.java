package com.example.TheNews.service;

import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.model.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public Comment createComment(CommentEntity comment, Long user_id, Long articleId);
    public Comment getOne(Long comment_id) throws NotFoundException;
    public Long delete(Long comment_id);

}
