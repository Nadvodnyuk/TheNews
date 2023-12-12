package com.example.TheNews.repository;

import com.example.TheNews.entity.Like;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<Like,Long> {

}