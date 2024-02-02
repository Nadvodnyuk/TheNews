package com.example.TheNews.controller;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.exception.UserAlreadyExistException;
import com.example.TheNews.exception.UserNotFoundException;
import com.example.TheNews.repository.ArticleRepo;
import com.example.TheNews.service.ArticleService;

// import org.hibernate.mapping.List;
import com.example.TheNews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")

public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @PostMapping
    public ResponseEntity createArticle(@RequestBody ArticleEntity art,
                                        @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(articleService.createArticle(art, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
    @GetMapping
    public ResponseEntity getArtByUserId(@RequestParam Long user_id) {
        try {
            return ResponseEntity.ok(articleService.getOne(user_id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteArticle(@PathVariable Long user_id) {
        try {
            return ResponseEntity.ok(articleService.delete(user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
