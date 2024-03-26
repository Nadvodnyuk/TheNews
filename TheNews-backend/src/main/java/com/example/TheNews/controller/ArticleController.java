package com.example.TheNews.controller;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ArticleService articleService;

    @DeleteMapping("/{user_id}")
    public ResponseEntity deleteArticle(@PathVariable Long user_id) {
        try {
            return ResponseEntity.ok(articleService.delete(user_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, статья");
        }
    }
}
