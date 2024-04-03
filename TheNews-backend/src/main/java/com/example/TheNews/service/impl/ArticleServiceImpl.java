package com.example.TheNews.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.ArticleRepo;
import com.example.TheNews.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleRepo articleRepo;

    //Для списка статей:
    public List<ArticleEntity> getAllLatestArticles() {
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(twentyFourHoursAgo);
        List<ArticleEntity> articlesLast24Hours = articleRepo.findByPublicationDateAfter(timestamp);
        return articlesLast24Hours;
    }

    //Для создания статьи:

    public void createArticle(String title, String text, String imageUrl) {
        // Создаем новую статью
        ArticleEntity article = new ArticleEntity();

        // Устанавливаем заголовок, текст, картинку, дату публикации
        article.setTitle(title);
        article.setArticle_text(text);
        article.setImage_url(imageUrl);
        java.sql.Timestamp currentDateTime = java.sql.Timestamp.valueOf(LocalDateTime.now());
        article.setPublication_date(currentDateTime);

        // Сохраняем статью в базе данных
        articleRepo.save(article);
    }

    //Для получения одной статьи:
    public ArticleEntity getOne(Long art_id) throws NotFoundException {
        ArticleEntity art = articleRepo.findById(art_id).get();
        if (art == null) {
            throw new NotFoundException("Пользователь не найден");
        }
        return art;
    }

    //Для изменения статьи
    public void editArticle(long article_id, String title, String text, String imageUrl) {
        // Создаем новую статью
        ArticleEntity article = articleRepo.findById(article_id).get();

        // Устанавливаем заголовок, текст, картинку, дату публикации
        article.setTitle(title);
        article.setArticle_text(text);
        article.setImage_url(imageUrl);
        java.sql.Timestamp currentDateTime = java.sql.Timestamp.valueOf(LocalDateTime.now());
        article.setPublication_date(currentDateTime);

        // Сохраняем статью в базе данных
        articleRepo.save(article);
    }
    
    //Удаление статьи:
    public Long delete(Long art_id) {
        articleRepo.deleteById(art_id);
        return art_id;
    }
}
