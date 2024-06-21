package com.example.TheNews.service.facade;

import com.example.TheNews.dto.request.LikeDto;
import org.springframework.stereotype.Service;
@Service
public interface LikeFacade {
    //C.Вывести лайки для статьи
    int likeNumFacade(long article_id);

    boolean isLikedBy(long user_id, long article_id);

    ///Для авторизованного:
    //C.Поставить лайк
    void putLikeFacade(LikeDto likeDto);

    //C.Удалить лайк
    void deleteLikeFacade(long userL, long articleL);
}
