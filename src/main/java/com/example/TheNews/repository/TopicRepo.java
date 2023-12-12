package com.example.TheNews.repository;

import com.example.TheNews.entity.Topic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topic,Long> {

}