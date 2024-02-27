package com.example.TheNews.repository;

import com.example.TheNews.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<LikeEntity, Long> {

}