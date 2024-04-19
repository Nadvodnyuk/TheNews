package com.example.TheNews.service;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.TopicEntity;
import com.example.TheNews.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service

public interface ArticleService {

    public List<ArticleEntity> getAllLatestArticles();

    public void createArticle(String title, String text, String imageUrl, Set<TopicEntity> topics);

    public ArticleEntity getOne(Long art_id) throws NotFoundException;

    public void editArticle(long article_id, String title, String text, String imageUrl);

    public void delete(Long art_id);
}
