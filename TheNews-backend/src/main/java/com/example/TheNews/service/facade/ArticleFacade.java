package com.example.TheNews.service.facade;
import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.Theme;
import org.springframework.stereotype.Service;
import com.example.TheNews.dto.request.CreateArticleDto;
import com.example.TheNews.dto.request.EditArticleDto;
import com.example.TheNews.dto.response.ArticleDto;
import com.example.TheNews.exception.NotFoundException;

import java.util.List;
import java.util.Set;

@Service
public interface ArticleFacade {
    //Для списка статей:
    List<ArticleDto> getAllLatestArticlesFacade();
    //Для создания статьи:

    void createArticleFacade(CreateArticleDto createArticleDto);

    //Для получения одной статьи:
    ArticleDto getOneFacade(Long art_id) throws NotFoundException;

    //Для изменения статьи
    void editArticleFacade(EditArticleDto editArticleDto, long article_id);

    //Удаление статьи:
    void deleteFacade(Long art_id);

    List<ArticleDto> getArticlesByUserPreferencesFacade(Set<Theme> favoriteTopics, Set<Theme> blockedTopics);
}
