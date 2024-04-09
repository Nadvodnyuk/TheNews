package com.example.TheNews.service.facade;

import com.example.TheNews.dto.request.CreateArticleDto;
import com.example.TheNews.dto.request.EditArticleDto;
import com.example.TheNews.dto.response.ArticleDto;
import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.exception.NotFoundException;

import java.util.List;

public interface ArticleFacade {
    //Для списка статей:
    public List<ArticleDto> getAllLatestArticlesFacade();
    //Для создания статьи:

    public void createArticleFacade(CreateArticleDto createArticleDto);

    //Для получения одной статьи:
    public ArticleDto getOneFacade(Long art_id) throws NotFoundException;

    //Для изменения статьи
    public void editArticleFacade(EditArticleDto editArticleDto);

    //Удаление статьи:
    public void deleteFacade(Long art_id);
}
