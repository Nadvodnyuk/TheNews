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

    @GetMapping("/showComms")
    public ResponseEntity<List<CommentDto>> getCommsForArticle() {}

    @PostMapping(/putComm)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CommentDto> putComm() {}

    @DeleteMapping("/user/{commentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> userDeleteComm() {}//удаление комма пользователем(с проверкой пользователяAuth.../Principal)

    @DeleteMapping("/admin/{commentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminDeleteComm() {}//удаление комма админом( без проверки(т.к. может удалить все коммы, а юзер только свои))
 


}
