package com.example.TheNews.service.facade.impl;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.Theme;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.ArticleService;
import com.example.TheNews.service.facade.ArticleFacade;
import com.example.TheNews.dto.request.CreateArticleDto;
import com.example.TheNews.dto.request.EditArticleDto;
import com.example.TheNews.dto.response.ArticleDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleFacadeImpl implements ArticleFacade {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ModelMapper modelMapper;

    //Для списка статей:
    public List<ArticleDto> getAllLatestArticlesFacade() {
        List<ArticleEntity> articles = articleService.getAllLatestArticles();
        Type listType = new TypeToken<List<ArticleDto>>() {
        }.getType();
        List<ArticleDto> ArticlesDto = new ModelMapper().map(articles, listType);
        return ArticlesDto;
    }

    //Для создания статьи:

    public void createArticleFacade(CreateArticleDto createArticleDto) {
        Set<Theme> themes = createArticleDto.getTopics().stream()
                .map(Theme::valueOf)
                .collect(Collectors.toSet());
        articleService.createArticle(createArticleDto.getTitle(),
                createArticleDto.getArticle_text(),
                createArticleDto.getImage_url(),
                themes);
    }

    //Для получения одной статьи:
    public ArticleDto getOneFacade(Long art_id) throws NotFoundException {
        ArticleEntity art = articleService.getOne(art_id);
        ArticleDto oneArticle = modelMapper.map(art, ArticleDto.class);
        return oneArticle;
    }

    //Для изменения статьи
    public void editArticleFacade(EditArticleDto editArticleDto,  long article_id) {
        editArticleDto.setArticle_id(article_id);
        Set<Theme> themes = editArticleDto.getTopics().stream()
                .map(Theme::valueOf)
                .collect(Collectors.toSet());
        System.out.println("themes"+themes);
        articleService.editArticle(editArticleDto.getArticle_id(),
                editArticleDto.getTitle(),
                editArticleDto.getArticle_text(),
                editArticleDto.getImage_url(),themes);
    }

    //Удаление статьи:
    public void deleteFacade(Long art_id) {
        articleService.delete(art_id);
    }


    public List<ArticleDto> getArticlesByUserPreferencesFacade(Authentication authentication) {
        List<ArticleEntity> articles = articleService.getFilteredArticlesByUserPreferences(authentication);
        Type listType = new TypeToken<List<ArticleDto>>() {
        }.getType();
        List<ArticleDto> ArticlesDto = new ModelMapper().map(articles, listType);
        return ArticlesDto;
    }
}