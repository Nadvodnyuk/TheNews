package com.example.TheNews.service.impl;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.repository.LikeRepo;
import com.example.TheNews.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepo likeRepo;

    public int getLikesByArticleId(ArticleEntity article_l) {
        // Поменяла на "найти количество лайков", не знаю, нужно или нет, можно потом вернуть
        List<LikeEntity> likeNum = likeRepo.findByArticleL(article_l);
        return likeNum.size();
    }

    public int getLikesByUserId(UserEntity user_l) {
        List<LikeEntity> likeNum = likeRepo.findByUserL(user_l);
        return likeNum.size();
    }

    public boolean isLikedByUserAndArticle(UserEntity user_l, ArticleEntity article_l) {
        boolean isLiked = likeRepo.existsByArticleLAndUserL(article_l, user_l);
        if (isLiked)
            return true;
        else
            return false;
    }

    public void createLike(UserEntity user_l, ArticleEntity article_l) {
        System.out.println("CreateLike ызван");
        if (isLikedByUserAndArticle(user_l, article_l)) {
            System.out.println("Лайк пользователя для данной статьи уже существует");
        } else {
            LikeEntity like = new LikeEntity();
            // Устанавливаем автора, текст, дату публикации коммента
            like.setUserL(user_l);
            like.setArticleL(article_l);
            // Сохраняем статью в базе данных
            likeRepo.save(like);
        }
    }

    public void delete(UserEntity user_l, ArticleEntity article_l) {
        likeRepo.deleteByArticleLAndUserL(article_l, user_l);
    }
}
