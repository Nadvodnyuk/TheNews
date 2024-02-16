package com.example.TheNews.service.impl;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.model.Like;
import com.example.TheNews.repository.ArticleRepo;
import com.example.TheNews.repository.LikeRepo;
import com.example.TheNews.repository.UserRepo;
import com.example.TheNews.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepo likeRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ArticleRepo articleRepo;

    public Like createLike(LikeEntity like, Long userId, Long articleId) {
        UserEntity user = userRepo.findById(userId).get();
        ArticleEntity art = articleRepo.findById(articleId).get();
        like.setUser_l(user);
        like.setArticle_l(art);
        return Like.toModel(likeRepo.save(like));
    }

    public Like getOne(Long like_id) throws NotFoundException {
        LikeEntity like = likeRepo.findById(like_id).get();
        if (like == null) {
            throw new NotFoundException("Лайк не найден");
        }
        return Like.toModel(like);
    }

    public Long delete(Long like_id) {
        likeRepo.deleteById(like_id);
        return like_id;
    }
}
