package com.example.TheNews.controller;

import com.example.TheNews.dto.request.LikeDto;
import com.example.TheNews.service.facade.LikeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class LikeController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LikeFacade likeFacade;

    //Ф.Вывести лайки для статьи

    ///Для авторизованного:
    //Ф.Поставить лайк

    //Ф.Удалить лайк

    @PostMapping("/auth/likes/likeNum")
    public ResponseEntity<?> likeNum(@RequestParam long article_id) {
        try {
            return ResponseEntity.ok(likeFacade.likeNumFacade(article_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/user/likes/postLike")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> postLike(@RequestBody LikeDto likeDto) {
        try {
            likeFacade.putLikeFacade(likeDto);
            return ResponseEntity.ok("Лайк успешно поставлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    //(с проверкой пользователяAuth.../Principal)

    //лайк удаляем взяв айди поста и Id пользователя, в функцию удалить прередаем оба
    @DeleteMapping("/user/likes/{userL}/{articleL}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteLike(@PathVariable long userL,
                                        @PathVariable long articleL) {
        try {
            LikeDto likeDto = new LikeDto();
            likeDto.setUserL(userL);
            likeDto.setArticleL(articleL);
            likeFacade.deleteLikeFacade(likeDto);
            return ResponseEntity.ok("Лайк успешно удален");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
//удаление лайка пользователем(с проверкой пользователяAuth.../Principal)

 
