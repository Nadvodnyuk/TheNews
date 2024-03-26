package com.example.TheNews.service;

import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.exception.NotFoundException;

import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    public Long delete(Long like_id);
}
