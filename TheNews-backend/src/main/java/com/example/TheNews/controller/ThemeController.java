package com.example.TheNews.controller;

import com.example.TheNews.service.ArticleService;
import com.example.TheNews.service.facade.ThemeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ThemeController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ThemeFacade themeFacade;

    @GetMapping("/user/themes")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllThemes() {
        try{
            return ResponseEntity.ok(themeFacade.getAllThemesFacade());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
