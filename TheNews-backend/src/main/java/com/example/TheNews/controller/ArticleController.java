package com.example.TheNews.controller;

import com.example.TheNews.dto.response.ArticleDto;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.ArticleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ArticleService articleService;

    @GetMapping()
    public ResponseEntity<List<ArticleDto>> getAllArticles() {}
    //Ф.Вытащить из БД все статьи за последние 24ч
    ///Вернуть СтатьиДТО

    @PostMapping("/create")
    @PreAuthorize("hasRole("ADMIN")")
    public ResponseEntity<?> createArticle() {}
    //Ф.Создать статью
    ///Вернуть СтатьиДТО

    @GetMapping("/{articleId}")
    @PreAuthorize("hasRole("ADMIN")")
    public ResponseEntity<ArticleDto> getArticleById() {}
    //Ф.Получить статью из БД
    //Ф.В ФАсаде статья преобразуется в ДТО
    ////Возвращает ДТО

    @PutMapping("/{articleId}")
    @PreAuthorize("hasRole("ADMIN")")
    public ResponseEntity<?> updateArticle() {}
    //Ф.Обновить статью

    @DeleteMapping("/{articleId}")
    @PreAuthorize("hasRole("ADMIN")")
    public ResponseEntity<?> deleteArticle () {}
    //Ф.Удалить статью по айди
}
