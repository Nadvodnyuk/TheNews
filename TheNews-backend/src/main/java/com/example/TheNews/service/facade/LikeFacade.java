package com.example.TheNews.service.facade;

import com.example.TheNews.dto.LikeDtoOld;
import org.springframework.http.ResponseEntity;

public interface LikeFacade {
    //C.Вывести лайки для статьи
    public int likeNumFacade(LikeDtoOld likeDto);
    ///Для авторизованного:
    //C.Поставить лайк
    public ResponseEntity<?> putLikeFacade(LikeDtoOld likeDto);
    //C.Удалить лайк
    public ResponseEntity<?> deleteLikeFacade(LikeDtoOld likeDto);
}
