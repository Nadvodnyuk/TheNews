package com.example.TheNews.service.facade;

import com.example.TheNews.dto.request.LikeDto;
import org.springframework.http.ResponseEntity;

public interface LikeFacade {
    //C.Вывести лайки для статьи
    public int likeNumFacade(long article_id);

    ///Для авторизованного:
    //C.Поставить лайк
    public void putLikeFacade(LikeDto likeDto);

    //C.Удалить лайк
    public void deleteLikeFacade(LikeDto likeDto);
}
