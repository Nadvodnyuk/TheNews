package com.example.TheNews.controller;

import com.example.TheNews.dto.request.CreateArticleDto;
import com.example.TheNews.dto.request.EditArticleDto;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.facade.ArticleFacade;
import org.springframework.security.access.prepost.PreAuthorize;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ArticleFacade articleFacade;

    @GetMapping("/articles/all")
    public ResponseEntity<?> getAllArticles() {
        try {
            return ResponseEntity.ok(articleFacade.getAllLatestArticlesFacade());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/admin/articles/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createArticle(@RequestBody CreateArticleDto article) {
        try {
            articleFacade.createArticleFacade(article);
            return ResponseEntity.ok("Статья успешно сохранена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/articles/article/{article_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getArticleById(@PathVariable long article_id) {
        try {
            return ResponseEntity.ok(articleFacade.getOneFacade(article_id));
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/articles/update/{article_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateArticle(@PathVariable long article_id,
                                           @RequestBody EditArticleDto article) {
        try {
            article.setArticle_id(article_id);
            articleFacade.editArticleFacade(article);
            return ResponseEntity.ok("Статья успешно обновлена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/articles/{article_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteArticle(@PathVariable long article_id) {
        try {
            articleFacade.deleteFacade(article_id);
            return ResponseEntity.ok("Статья успешно удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
