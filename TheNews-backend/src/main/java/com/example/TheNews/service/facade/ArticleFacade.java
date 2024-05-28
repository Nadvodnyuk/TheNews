package com.example.TheNews.service.facade;
import org.springframework.stereotype.Service;
import com.example.TheNews.dto.request.CreateArticleDto;
import com.example.TheNews.dto.request.EditArticleDto;
import com.example.TheNews.dto.response.ArticleDto;
import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.exception.NotFoundException;

import java.util.List;
@Service
public interface ArticleFacade {
    //Для списка статей:
    List<ArticleDto> getAllLatestArticlesFacade();
    //Для создания статьи:

    void createArticleFacade(CreateArticleDto createArticleDto);

    //Для получения одной статьи:
    ArticleDto getOneFacade(Long art_id) throws NotFoundException;

    //Для изменения статьи
    void editArticleFacade(EditArticleDto editArticleDto);

    //Удаление статьи:
    void deleteFacade(Long art_id);


}
