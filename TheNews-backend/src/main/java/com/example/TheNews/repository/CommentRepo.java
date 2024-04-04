package com.example.TheNews.repository;
import java.util.List;
import com.example.TheNews.entity.CommentEntity;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByArticleId(long article_id, PageRequest pageRequest);
}