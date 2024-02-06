package com.example.TheNews.controller;


import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.exception.UserNotFoundException;
import com.example.TheNews.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping
    public ResponseEntity createLike(@RequestBody LikeEntity like,
                                     @RequestParam Long userId,
                                     @RequestParam Long articleId) {
        try {
            return ResponseEntity.ok(likeService.createLike(like, userId, articleId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, лайк");
        }
    }

    @GetMapping
    public ResponseEntity getLikeByUserId(@RequestParam Long user_id) {
        try {
            return ResponseEntity.ok(likeService.getOne(user_id));
        } catch (UserNotFoundException e) {
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
