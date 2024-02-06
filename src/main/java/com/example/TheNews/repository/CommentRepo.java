package com.example.TheNews.repository;

import com.example.TheNews.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {

}