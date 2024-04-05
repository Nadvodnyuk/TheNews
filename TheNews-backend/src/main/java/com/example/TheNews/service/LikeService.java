package com.example.TheNews.service;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.LikeEntity;
import java.util.List;

import com.example.TheNews.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    public List<LikeEntity> getLikesByArticleId(ArticleEntity article_l);
    public void createLike(UserEntity user_l, ArticleEntity article_l);
    public Long delete(Long like_id);
}
