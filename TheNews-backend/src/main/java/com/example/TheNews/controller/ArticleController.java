package com.example.TheNews.controller;

import com.example.TheNews.dto.request.CreateArticleDto;
import com.example.TheNews.dto.request.EditArticleDto;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.facade.ArticleFacade;
import org.springframework.security.access.prepost.PreAuthorize;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ArticleController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ArticleFacade articleFacade;


    @GetMapping("/auth/articles/all")
    public ResponseEntity<?> getAllArticles() {
        try {
            return ResponseEntity.ok(articleFacade.getAllLatestArticlesFacade());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


    @PostMapping("/admin/articles/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createArticle(@RequestBody CreateArticleDto article) {
        try {
            articleFacade.createArticleFacade(article);
            return ResponseEntity.ok("Статья успешно сохранена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


    @GetMapping("/admin/articles/{article_id}")
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


    @PutMapping("/admin/articles/update/{article_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateArticle(@PathVariable long article_id,
                                           @RequestBody EditArticleDto article) {
        try {
            articleFacade.editArticleFacade(article, article_id);
            return ResponseEntity.ok("Статья успешно обновлена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


    @DeleteMapping("/admin/articles/{article_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteArticle(@PathVariable long article_id) {
        try {
            articleFacade.deleteFacade(article_id);
            return ResponseEntity.ok("Статья успешно удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/auth/articles/filtered")
    public ResponseEntity<?> getFilteredArticles(
            Authentication authentication) {
        try {

            return ResponseEntity.ok(articleFacade.getArticlesByUserPreferencesFacade(authentication));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
