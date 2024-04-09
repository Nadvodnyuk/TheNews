package com.example.TheNews.controller;

import com.example.TheNews.dto.request.SignUpDto;
import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.dto.LikeDtoOld;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.LikeService;
import com.example.TheNews.service.facade.LikeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LikeService likeService;
    @Autowired
    private LikeFacade likeFacade;

    //Ф.Вывести лайки для статьи

    ///Для авторизованного:
    //Ф.Поставить лайк

    //Ф.Удалить лайк

    @GetMapping("/likeNum")
    public ResponseEntity<?> likeNum(@RequestBody LikeDtoOld likeDto) {
        return ResponseEntity.ok(likeFacade.likeNumFacade(likeDto));
    }

    @PostMapping("/putLike")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> putLike(@RequestBody LikeDtoOld likeDto) {
        return likeFacade.putLikeFacade(likeDto);
    }
    //(с проверкой пользователяAuth.../Principal)

    //лайк удаляем взяв айди поста и айди пользователя, в функцию удалить прередаем оба
    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteLikeFacade(@RequestBody LikeDtoOld likeDto){
        return likeFacade.putLikeFacade(likeDto);
    }
}
    //удаление лайка пользователем(с проверкой пользователяAuth.../Principal)

 
