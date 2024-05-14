package com.example.TheNews.controller;

import com.example.TheNews.dto.request.CreateCommentDto;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.CommentService;
import com.example.TheNews.service.facade.CommentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
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


    @GetMapping("/auth/comments/showComments")
    public ResponseEntity<?> getCommentsForArticle(@RequestParam int page,
                                                   @RequestParam long article_с) {
        try {
            return ResponseEntity.ok(commentFacade.getCommentsByArticleIdWithPaginationFacade(article_с, page));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/user/comments/postComment")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> postComment(
            @RequestParam long user_id,
            @RequestParam long article_id,
                                        @RequestBody CreateCommentDto comment) {
        try {
            System.out.println("userC " + user_id);
            System.out.println("articleC " + article_id);
            System.out.println("comment " +comment);
            commentFacade.createCommentFacade(user_id, comment, article_id);
            return ResponseEntity.ok("Комментарий успешно сохранен");
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    //удаление комма пользователем(с проверкой пользователяAuth.../Principal)

    @DeleteMapping("/admin/comments/{comment_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteComment(@PathVariable long comment_id) {
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
