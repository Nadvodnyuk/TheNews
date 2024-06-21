package com.example.TheNews.repository;

import com.example.TheNews.entity.ArticleEntity;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<ArticleEntity, Long> {
    List<ArticleEntity> findByPublicationDateAfter(Timestamp date);
}