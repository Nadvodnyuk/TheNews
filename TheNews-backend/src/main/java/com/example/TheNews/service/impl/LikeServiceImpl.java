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
        List<LikeEntity> likeNum = likeRepo.findByArticleId(article_l);
        return likeNum.size();
    }


    public boolean isLikedByUserAndArticle(UserEntity user_l, ArticleEntity article_l) {
        List<LikeEntity> isLiked = likeRepo.findByArticleIdAndUserId(article_l, user_l);
        if (isLiked.isEmpty())
            return false;
        else
            return true;
    }

    public void createLike(UserEntity user_l, ArticleEntity article_l) {
        // Создаем лайк
        LikeEntity like = new LikeEntity();

        // Устанавливаем автора, текст, дату публикации коммента
        like.setUserL(user_l);
        like.setArticleL(article_l);
        // Сохраняем статью в базе данных
        likeRepo.save(like);
    }

    public void delete(UserEntity user_l, ArticleEntity article_l) {
        likeRepo.deleteByArticleIdAndUserId(article_l, user_l);
    }
}
