package com.example.TheNews.controller;

import com.example.TheNews.dto.request.CreateCommentDto;
import com.example.TheNews.dto.request.DeleteCommentDto;
import com.example.TheNews.dto.request.LikeDto;
import com.example.TheNews.dto.response.CommentDto;
import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.CommentService;
import com.example.TheNews.service.facade.CommentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentFacade commentFacade;

    //Ф.Вывести комменты для статьи

    ///Для авторизованного:
    //Ф.Написать коммент

    ///Admin:
    //Удалить коммент


    @GetMapping("/showComments")
    public ResponseEntity<?> getCommentsForArticle(@RequestParam int page,
                                                   @RequestParam long article_с) {
        try {
            return ResponseEntity.ok(commentFacade.getCommentsByArticleIdWithPaginationFacade(article_с, page));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/putComment/{userC}/{articleC}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> putComment(
                                        @PathVariable long userC,
                                        @PathVariable long articleC,
                                        @RequestBody CreateCommentDto comment) {
        try {
            commentFacade.createCommentFacade(userC, comment, articleC);
            return ResponseEntity.ok("Комментарий успешно сохранен");
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    //удаление комма пользователем(с проверкой пользователяAuth.../Principal)

    @DeleteMapping("/admin/{comment_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> DeleteComment(@PathVariable long comment_id) {
        try {
            commentFacade.deleteFacade(comment_id);
            return ResponseEntity.ok("Комментарий успешно удален");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    //удаление комма админом( без проверки(т.к. может удалить все коммы, а юзер только свои))
//


}
