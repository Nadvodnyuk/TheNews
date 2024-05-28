package com.example.TheNews.dto;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(ArticleService articleService) {
        return args -> {
            Instant now = Instant.now();

            ArticleEntity article1 = ArticleEntity.builder()
                    .title("Жара в Петербурге во вторник преодолела отметку в 25 градусов уже к 9 утра")
                    .topics("#Погода #СПб")
                    .article_text("Article text 1")
                    .image_url("/img/heat.jpg")
                    .like_num(0)
                    .comment_num(0)
                    .publicationDate(Timestamp.from(now.minus(60, ChronoUnit.MINUTES)))
                    .build();

            ArticleEntity article2 = ArticleEntity.builder()
                    .title("В Петербурге начался сезон клещей")
                    .topics("#Клещи")
                    .article_text("Article text 2")
                    .image_url("/img/forest.jpg")
                    .like_num(0)
                    .comment_num(0)
                    .publicationDate(Timestamp.from(now.minus(45, ChronoUnit.MINUTES)))
                    .build();

            ArticleEntity article3 = ArticleEntity.builder()
                    .title("Всё о параде Международного дня защиты детей")
                    .topics("Topic 3")
                    .article_text("Article text 3")
                    .image_url("/img/kids.jpg")
                    .like_num(0)
                    .comment_num(0)
                    .publicationDate(Timestamp.from(now.minus(30, ChronoUnit.MINUTES)))
                    .build();

            ArticleEntity article4 = ArticleEntity.builder()
                    .title("В Петербурге в июне ожидается начало сезона дождей")
                    .topics("#Погода #СПб")
                    .article_text("Article text 4")
                    .image_url("/img/rain.jpg")
                    .like_num(0)
                    .comment_num(0)
                    .publicationDate(Timestamp.from(now.minus(15, ChronoUnit.MINUTES)))
                    .build();

            ArticleEntity article5 = ArticleEntity.builder()
                    .title("В Петербурге 60% пастеризованного молока разбавляют водой")
                    .topics("#Еда #СПб")
                    .article_text("Article text 5")
                    .image_url("/img/milk.jpg")
                    .like_num(0)
                    .comment_num(0)
                    .publicationDate(Timestamp.from(now))
                    .build();

            articleService.saveArticles(Arrays.asList(article1, article2, article3, article4, article5));
        };
    }
}