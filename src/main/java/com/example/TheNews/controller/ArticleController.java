package com.example.TheNews.controller;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.ArticleService;
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
            @RequestParam Long user_id) {
        try {
            return ResponseEntity.ok(articleService.createArticle(art, user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, статья" + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getArtByUserId(@RequestParam Long user_id) {
        try {
            return ResponseEntity.ok(articleService.getOne(user_id));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, статья");
        }
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity deleteArticle(@PathVariable Long user_id) {
        try {
            return ResponseEntity.ok(articleService.delete(user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, статья");
        }
    }
}
