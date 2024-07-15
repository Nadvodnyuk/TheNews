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

    //ок
    @PostMapping("/auth/comments/commentNum")
    public ResponseEntity<?> commentNum(@RequestParam long article_id) {
        try {
            return ResponseEntity.ok(commentFacade.commentNumFacade(article_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    //ок
    @GetMapping("/auth/comments/showComments/{article_id}/{page}")
    public ResponseEntity<?> getCommentsForArticle(@PathVariable long article_id,
                                                   @PathVariable int page) {
        try {
            return ResponseEntity.ok(commentFacade.getCommentsByArticleIdWithPaginationFacade(article_id, page));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    //ок
    @PostMapping("/both/comments/postComment")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> postComment(
            @RequestParam long user_id,
            @RequestParam long article_id,
                                        @RequestBody CreateCommentDto comment) {
        try {
            commentFacade.createCommentFacade(user_id, comment, article_id);
            return ResponseEntity.ok("Комментарий успешно сохранен");
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    //ок
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
}
