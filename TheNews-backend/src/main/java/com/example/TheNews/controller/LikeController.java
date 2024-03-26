package com.example.TheNews.controller;

import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.dto.LikeDtoOld;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LikeService likeService;



    @DeleteMapping("/{user_id}")
    public ResponseEntity deleteLike(@PathVariable Long user_id) {
        try {
            return ResponseEntity.ok(likeService.delete(user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, лайк");
        }
    }
}
