package com.example.TheNews.service;

import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public Long delete(Long comment_id);

}
