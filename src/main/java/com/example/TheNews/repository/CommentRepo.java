package com.example.TheNews.repository;

import com.example.TheNews.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {

}