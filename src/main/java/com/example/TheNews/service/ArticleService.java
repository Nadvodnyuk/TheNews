package com.example.TheNews.service;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.UserNotFoundException;
import com.example.TheNews.model.Article;
import com.example.TheNews.repository.ArticleRepo;
import com.example.TheNews.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ArticleRepo articleRepo;

    public Article createArticle(ArticleEntity art, Long userId) {
        UserEntity user = userRepo.findById(userId).get();
        art.setUser_a(user);
        return Article.toModel(articleRepo.save(art));
    }

    public Article getOne(Long art_id) throws UserNotFoundException {
        ArticleEntity art = articleRepo.findById(art_id).get();
        if (art == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return Article.toModel(art);
    }

    public Long delete(Long art_id) {
        articleRepo.deleteById(art_id);
        return art_id;
    }
}
