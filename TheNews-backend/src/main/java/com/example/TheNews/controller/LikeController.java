package com.example.TheNews.controller;

import com.example.TheNews.dto.request.DeleteCommentDto;
import com.example.TheNews.dto.request.DeleteUserDto;
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
import org.springframework.context.annotation.Bean;

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

    @PostMapping("/likeNum")
    public ResponseEntity<?> likeNum(@RequestParam long article_id) {
        try {
            return ResponseEntity.ok(likeFacade.likeNumFacade(article_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/putLike")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> putLike(@RequestBody LikeDto likeDto) {
        try {
            likeFacade.putLikeFacade(likeDto);
            return ResponseEntity.ok("Лайк успешно поставлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    //(с проверкой пользователяAuth.../Principal)

    //лайк удаляем взяв айди поста и Id пользователя, в функцию удалить прередаем оба
    @DeleteMapping("/{userL}/{articleL}")
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

 
