package com.example.TheNews.repository;

import com.example.TheNews.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<ArticleEntity,Long> {

}