package com.example.TheNews.repository;

import com.example.TheNews.entity.Article;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<Article,Long> {

}