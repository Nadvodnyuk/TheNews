package com.example.TheNews.controller;

import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.exception.UserNotFoundException;
import com.example.TheNews.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity createComment(@RequestBody CommentEntity comment,
                                     @RequestParam Long user_id,
                                     @RequestParam Long article_id) {
        try {
            return ResponseEntity.ok(commentService.createComment(comment, user_id, article_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, комментарий");
        }
    }

    @GetMapping
    public ResponseEntity getCommentByUserId(@RequestParam Long user_id) {
        try {
            return ResponseEntity.ok(commentService.getOne(user_id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, комментарий");
        }
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity deleteComment(@PathVariable Long user_id) {
        try {
            return ResponseEntity.ok(commentService.delete(user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, комментарий");
        }
    }

}
