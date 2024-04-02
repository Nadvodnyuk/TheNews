package com.example.TheNews.controller;

import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.dto.LikeDtoOld;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.LikeService;
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
    private LikeService likeService;



    @PostMapping("/putLike")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<LikeDtoOld> putComm() {}
    //(с проверкой пользователяAuth.../Principal)

    //лайк удаляем взяв айди поста и айди пользователя, в функцию удалить прередаем оба
    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> userDeleteLike() {}
    //удаление лайка пользователем(с проверкой пользователяAuth.../Principal)

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminDeleteLike() {}
//удаление лайка админом( без проверки(т.к. может удалить все коммы, а юзер только свои))
 
