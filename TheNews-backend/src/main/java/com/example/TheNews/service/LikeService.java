package com.example.TheNews.service;

import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.model.Like;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    public Like createLike(LikeEntity like, Long userId, Long articleId);
    public Like getOne(Long like_id) throws NotFoundException;
    public Long delete(Long like_id);
}
