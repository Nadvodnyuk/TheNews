package com.example.TheNews.controller;

import com.example.TheNews.dto.response.ThemeDto;
import com.example.TheNews.entity.Theme;
import com.example.TheNews.service.ArticleService;
import com.example.TheNews.service.UserService;
import com.example.TheNews.service.facade.ArticleFacade;
import com.example.TheNews.service.facade.ThemeFacade;
import com.example.TheNews.service.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ThemeController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ThemeFacade themeFacade;

    @GetMapping("/admin/themes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllThemes() {
        try{
            return ResponseEntity.ok(themeFacade.getAllThemesFacade());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }




//    @PostMapping("/admin/themes/{id}")
//    public ResponseEntity<?> setArticleThemes(@PathVariable Long id, @RequestBody Set<Theme> themes) {
//        try {
//            return ResponseEntity.ok(themeFacade.setArticleThemes(id, themes));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e);
//        }
//
//    }
}
