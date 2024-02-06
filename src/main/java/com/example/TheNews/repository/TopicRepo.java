package com.example.TheNews.repository;

import com.example.TheNews.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<TopicEntity,Long> {
    TopicEntity findByName(String name);
}