package com.example.TheNews.controller;


import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping
    public ResponseEntity createLike(@RequestBody LikeEntity like, @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(likeService.createLike(like, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
