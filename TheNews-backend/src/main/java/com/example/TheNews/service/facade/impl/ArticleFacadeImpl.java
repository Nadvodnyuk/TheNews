package com.example.TheNews.service.facade.impl;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.ArticleRepo;
import com.example.TheNews.service.ArticleService;
import com.example.TheNews.service.CommentService;
import com.example.TheNews.service.UserService;
import com.example.TheNews.service.facade.ArticleFacade;
import com.example.TheNews.dto.request.CreateArticleDto;
import com.example.TheNews.dto.request.EditArticleDto;
import com.example.TheNews.dto.response.ArticleDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.List;

public class ArticleFacadeImpl implements ArticleFacade {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ModelMapper modelMapper;
    /*
    Вытащить из БД все статьи за 24ч  {
        С.Получить все статьи
        Вернуть в ДТОСтатьи
    }
    //Создать статью 
        С.Создать статью

    //Получитьт статью по ИД
        С.Получить статью по айди
        Вернуть в ДТОСтатью

    //Изменить статью
        С.Изменить статью

    //Удалить статью по ИД
        С.Удалить статью по ИД
    */

    //Для списка статей:
    public List<ArticleDto> getAllLatestArticlesFacade() {
        List<ArticleEntity> articles = articleService.getAllLatestArticles();
        // Create Conversion Type
        Type listType = new TypeToken<List<ArticleDto>>() {
        }.getType();
        // Convert List of Entity objects to a List of DTOs objects
        List<ArticleDto> ArticlesDto = new ModelMapper().map(articles, listType);
        return ArticlesDto;
    }

    //Для создания статьи:

    public void createArticleFacade(CreateArticleDto createArticleDto) {
        articleService.createArticle(createArticleDto.getTitle(),
                createArticleDto.getArticle_text(),
                createArticleDto.getImage_url());
    }

    //Для получения одной статьи:
    public ArticleDto getOneFacade(Long art_id) throws NotFoundException {
        ArticleEntity art = articleService.getOne(art_id);
        ArticleDto oneArticle = modelMapper.map(art, ArticleDto.class);
        return oneArticle;
    }

    //Для изменения статьи
    public void editArticleFacade(EditArticleDto editArticleDto) {
        articleService.editArticle(editArticleDto.getArticle_id(),
                editArticleDto.getTitle(),
                editArticleDto.getArticle_text(),
                editArticleDto.getImage_url());
    }

    //Удаление статьи:
    public void deleteFacade(Long art_id) {
        articleService.delete(art_id);
    }
}