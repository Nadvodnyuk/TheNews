package com.example.TheNews.service.facade.impl;

import com.example.TheNews.dto.request.LikeDto;
import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.ArticleService;
import com.example.TheNews.service.LikeService;
import com.example.TheNews.service.UserService;
import com.example.TheNews.service.facade.LikeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class LikeFacadeImpl implements LikeFacade {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private LikeService likeService;

    @Autowired
    private UserService userService;

    //C.Вывести кол-во лайков
    public int likeNumFacade(long article_id) {

        try {
            ArticleEntity art = articleService.getOne(article_id);
            return likeService.getLikesByArticleId(art);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Лайкнутые посты пользователем
    public boolean isLikedBy(long user_id, long article_id) {
        try {
            ArticleEntity art = articleService.getOne(article_id);
            UserEntity user = userService.getOne(user_id);
            return likeService.isLikedByUserAndArticle(user, art);

        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ///Для авторизованного:
    //C.Поставить лайк
    public void putLikeFacade(LikeDto likeDto){
        try {
            ArticleEntity art = articleService.getOne(likeDto.getArticleL());
            UserEntity user = userService.getOne(likeDto.getUserL());
            likeService.createLike(user, art);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //C.Удалить лайк
    public void deleteLikeFacade(LikeDto likeDto){
        try {
            ArticleEntity art = articleService.getOne(likeDto.getArticleL());
            UserEntity user = userService.getOne(likeDto.getUserL());
            likeService.delete(user, art);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
