package com.example.TheNews.service.impl;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.ArticleRepo;
import com.example.TheNews.repository.CommentRepo;
import com.example.TheNews.repository.UserRepo;
import com.example.TheNews.service.ArticleService;
import com.example.TheNews.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ArticleRepo articleRepo;


    public Long delete(Long comment_id) {
        commentRepo.deleteById(comment_id);
        return comment_id;
    }
}
