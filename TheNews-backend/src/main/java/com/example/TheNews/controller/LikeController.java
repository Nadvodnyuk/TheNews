package com.example.TheNews.controller;

import com.example.TheNews.dto.request.DeleteCommentDto;
import com.example.TheNews.dto.request.LikeDto;
import com.example.TheNews.service.ArticleService;
import com.example.TheNews.service.LikeService;
import com.example.TheNews.service.UserService;
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
    private LikeFacade likeFacade;

    //Ф.Вывести лайки для статьи

    ///Для авторизованного:
    //Ф.Поставить лайк

    //Ф.Удалить лайк

    @GetMapping("/likeNum")
    public ResponseEntity<?> likeNum(@RequestBody long article_id) {
        try {
            return ResponseEntity.ok(likeFacade.likeNumFacade(article_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/putLike")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> putLike(@RequestBody LikeDto likeDto) {
        try {
            likeFacade.putLikeFacade(likeDto);
            return ResponseEntity.ok("Лайк успешно удален");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
    //(с проверкой пользователяAuth.../Principal)

    //лайк удаляем взяв айди поста и Id пользователя, в функцию удалить прередаем оба
    @DeleteMapping("/delete/{article_id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteLikeFacade(@RequestBody LikeDto likeDto,
                                              @PathVariable long article_id) {
        try {
            likeFacade.deleteLikeFacade(likeDto);
            return ResponseEntity.ok("Лайк успешно удален");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
//удаление лайка пользователем(с проверкой пользователяAuth.../Principal)

 
