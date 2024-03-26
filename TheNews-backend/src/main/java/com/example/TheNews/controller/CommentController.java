package com.example.TheNews.controller;

import com.example.TheNews.dto.CommentDtoOld;
import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity createComment(@RequestBody CommentEntity comment,
                                        @RequestParam Long user_id,
                                        @RequestParam Long article_id,
                                        @RequestBody CommentDtoOld commentDto) {
        try {
            CommentEntity commentRequest = modelMapper.map(commentDto, CommentEntity.class);
            CommentDtoOld commentResponse = modelMapper.map(
                    commentService.createComment(comment, user_id, article_id), CommentDtoOld.class);
            return ResponseEntity.ok(commentResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, комментарий");
        }
    }

    @GetMapping
    public ResponseEntity getCommentByUserId(@RequestParam Long user_id) {
        try {
            CommentDtoOld commentResponse = modelMapper.map(commentService.getOne(user_id), CommentDtoOld.class);
            return ResponseEntity.ok(commentResponse);
        } catch (NotFoundException e) {
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
