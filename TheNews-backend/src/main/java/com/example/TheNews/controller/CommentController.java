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

 

    @DeleteMapping("/{user_id}")
    public ResponseEntity deleteComment(@PathVariable Long user_id) {
        try {
            return ResponseEntity.ok(commentService.delete(user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, комментарий");
        }
    }

}
