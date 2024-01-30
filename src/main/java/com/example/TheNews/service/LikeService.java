package com.example.TheNews.service;

import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.model.Like;
import com.example.TheNews.repository.LikeRepo;
import com.example.TheNews.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepo likeRepo;
    @Autowired
    private UserRepo userRepo;

    public Like createLike(LikeEntity like, Long userId) {
        UserEntity user = userRepo.findById(userId).get();
        like.setUser_l(user);
        return Like.toModel(likeRepo.save(like));
    }
}
