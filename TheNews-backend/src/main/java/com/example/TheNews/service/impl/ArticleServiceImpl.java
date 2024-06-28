package com.example.TheNews.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.TheNews.entity.Theme;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.ArticleRepo;
import com.example.TheNews.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private UserRepo userRepo;

    //Для списка статей:
    public List<ArticleEntity> getAllLatestArticles() {
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(twentyFourHoursAgo);
        List<ArticleEntity> articlesLast24Hours = articleRepo.findByPublicationDateAfter(timestamp);
        return articlesLast24Hours;
    }

    //Для создания статьи:

    public void createArticle(String title, String text, String imageUrl, Set<Theme> topics) {
        ArticleEntity article = new ArticleEntity();

        article.setTitle(title);
        article.setArticle_text(text);
        article.setImage_url(imageUrl);
        java.sql.Timestamp currentDateTime = java.sql.Timestamp.valueOf(LocalDateTime.now());
        article.setPublicationDate(currentDateTime);
        article.setTopics(topics);

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
        ArticleEntity article = articleRepo.findById(article_id).get();

        article.setTitle(title);
        article.setArticle_text(text);
        article.setImage_url(imageUrl);
        java.sql.Timestamp currentDateTime = java.sql.Timestamp.valueOf(LocalDateTime.now());
        article.setPublicationDate(currentDateTime);
        article.setTopics(topics);

        articleRepo.save(article);
    }


    public List<ArticleEntity> getFilteredArticlesByUserPreferences(Authentication authentication) {
        List<ArticleEntity> articlesLast24Hours = getAllLatestArticles();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Optional<UserEntity> userOptional = userRepo.findByUsername(username);

            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                Set<Theme> favoriteTopics = user.getFavoriteTopics();
                Set<Theme> blockedTopics = user.getBlockedTopics();

                List<ArticleEntity> filteredArticles = articlesLast24Hours.stream()
                        .filter(article -> {
                            boolean containsBlockedTopic = article.getTopics().stream()
                                    .anyMatch(blockedTopics::contains);
                            return !containsBlockedTopic;
                        })
                        .sorted((a1, a2) -> {
                            long countA1 = a1.getTopics().stream().filter(favoriteTopics::contains).count();
                            long countA2 = a2.getTopics().stream().filter(favoriteTopics::contains).count();
                            return Long.compare(countA2, countA1);
                        })
                        .collect(Collectors.toList());
                Collections.reverse(filteredArticles);
                return filteredArticles;
            }
        }

        return articlesLast24Hours;
    }



    //Удаление статьи:
    public void delete(Long art_id) {
        articleRepo.deleteById(art_id);
    }

    public void saveArticles(List<ArticleEntity> articles) {
        articleRepo.saveAll(articles);
    }
}
