package com.example.TheNews.service;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service

public interface ArticleService {

    public Long delete(Long art_id);
}
