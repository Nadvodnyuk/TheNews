package com.example.TheNews.controller;

import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.dto.LikeDto;
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

    @PostMapping
    public ResponseEntity createLike(@RequestBody LikeEntity like,
                                     @RequestParam Long user_id,
                                     @RequestParam Long article_id,
                                     @RequestBody LikeDto likeDto) {
        try {
            LikeEntity likeRequest = modelMapper.map(likeDto, LikeEntity.class);
            LikeDto likeResponse = modelMapper.map(
                    likeService.createLike(like, user_id, article_id), LikeDto.class);
            return ResponseEntity.ok(likeResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, лайк");
        }
    }

    @GetMapping
    public ResponseEntity getLikeByUserId(@RequestParam Long user_id) {
        try {
            LikeDto likeResponse = modelMapper.map(likeService.getOne(user_id), LikeDto.class);
            return ResponseEntity.ok(likeResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, лайк");
        }
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity deleteLike(@PathVariable Long user_id) {
        try {
            return ResponseEntity.ok(likeService.delete(user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, лайк");
        }
    }
}
