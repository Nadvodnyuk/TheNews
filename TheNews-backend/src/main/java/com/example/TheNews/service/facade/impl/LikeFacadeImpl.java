package com.example.TheNews.service.facade.impl;

import com.example.TheNews.dto.LikeDtoOld;
import com.example.TheNews.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class LikeFacadeImpl {
    @Autowired
    private LikeService likeService;
    //C.Вывести кол-во лайков
    public int likeNumFacade(LikeDtoOld likeDto){
        return likeService.getLikesByArticleId(likeDto.getArticleL());
    }
    ///Для авторизованного:
    //C.Поставить лайк
    public ResponseEntity<?> putLikeFacade(LikeDtoOld likeDto){
        likeService.createLike(likeDto.getUserL(), likeDto.getArticleL());

        return ResponseEntity.ok(likeService.isLikedByUserAndArticle(likeDto.getUserL(), likeDto.getArticleL()));
    }

    //C.Удалить лайк
    public ResponseEntity<?> deleteLikeFacade(LikeDtoOld likeDto){
        likeService.delete(likeDto.getUserL(), likeDto.getArticleL());
        return ResponseEntity.ok(!likeService.isLikedByUserAndArticle(likeDto.getUserL(), likeDto.getArticleL()));
    }
}
