package com.example.TheNews.service.impl;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.model.Comment;
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

    public Comment createComment(CommentEntity comment, Long user_id, Long articleId) {
        UserEntity user = userRepo.findById(user_id).get();
        ArticleEntity art = articleRepo.findById(articleId).get();
        comment.setUser_c(user);
        comment.setArticle_c(art);
        return Comment.toModel(commentRepo.save(comment));
    }

    public Comment getOne(Long comment_id) throws NotFoundException {
        CommentEntity comment = commentRepo.findById(comment_id).get();
        if (comment == null) {
            throw new NotFoundException("Комментарий не найден");
        }
        return Comment.toModel(comment);
    }

    public Long delete(Long comment_id) {
        commentRepo.deleteById(comment_id);
        return comment_id;
    }
}
