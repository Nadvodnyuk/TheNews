package com.example.TheNews.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.TheNews.entity.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.ArticleRepo;
import com.example.TheNews.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    //Для списка статей:
    public List<ArticleEntity> getAllLatestArticles() {
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(twentyFourHoursAgo);
        List<ArticleEntity> articlesLast24Hours = articleRepo.findByPublicationDateAfter(timestamp);
        return articlesLast24Hours;
    }

    //Для создания статьи:

    public void createArticle(String title, String text, String imageUrl, Set<Theme> topics) {
        // Создаем новую статью
        ArticleEntity article = new ArticleEntity();
        // Устанавливаем заголовок, текст, картинку, дату публикации
        article.setTitle(title);
        article.setArticle_text(text);
        article.setImage_url(imageUrl);
        java.sql.Timestamp currentDateTime = java.sql.Timestamp.valueOf(LocalDateTime.now());
        article.setPublicationDate(currentDateTime);
        article.setTopics(topics);

        // Сохраняем статью в базе данных
        articleRepo.save(article);
    }

    //Для получения одной статьи:
    public ArticleEntity getOne(Long art_id) throws NotFoundException {
        ArticleEntity art = articleRepo.findById(art_id).get();
        if (art == null) {
            throw new NotFoundException("Пользователь не найден");
        }
        return art;
    }

    //Для изменения статьи
    public void editArticle(long article_id, String title, String text, String imageUrl, Set<Theme> topics) {
        // Ищем статью
        ArticleEntity article = articleRepo.findById(article_id).get();

        // Устанавливаем заголовок, текст, картинку, дату публикации
        article.setTitle(title);
        article.setArticle_text(text);
        article.setImage_url(imageUrl);
        java.sql.Timestamp currentDateTime = java.sql.Timestamp.valueOf(LocalDateTime.now());
        article.setPublicationDate(currentDateTime);
//        article.setTopics(topics);

        // Сохраняем статью в базе данных
        articleRepo.save(article);
    }

    //эксперименты
    public List<ArticleEntity> getArticlesByUserPreferences(Set<Theme> favoriteTopics, Set<Theme> blockedTopics) {
        // Получаем все статьи из базы данных
        List<ArticleEntity> allArticles = articleRepo.findAll();

        // Фильтруем статьи по предпочитаемым и запрещенным темам пользователя
        List<ArticleEntity> filteredArticles = allArticles.stream()
                .filter(article -> {
                    // Проверяем наличие хотя бы одной предпочитаемой темы в статье
                    boolean containsFavoriteTopic = article.getTopics().stream()
                            .anyMatch(favoriteTopics::contains);

                    // Проверяем наличие хотя бы одной запрещенной темы в статье
                    boolean containsBlockedTopic = article.getTopics().stream()
                            .anyMatch(blockedTopics::contains);

                    // Возвращаем true только если статья не содержит запрещенные темы и содержит хотя бы одну предпочитаемую тему
                    return !containsBlockedTopic && containsFavoriteTopic;
                })
                .collect(Collectors.toList());

        // Сортируем статьи по количеству совпадающих тем с предпочтениями пользователя (от большего к меньшему)

        filteredArticles.sort((a1, a2) -> {
            long countA1 = a1.getTopics().stream().filter(favoriteTopics::contains).count();
            long countA2 = a2.getTopics().stream().filter(favoriteTopics::contains).count();
            return Long.compare(countA2, countA1);
        });

        return filteredArticles;
    }

    //Удаление статьи:
    public void delete(Long art_id) {
        articleRepo.deleteById(art_id);
    }

    public void saveArticles(List<ArticleEntity> articles) {
        articleRepo.saveAll(articles);
    }
}
