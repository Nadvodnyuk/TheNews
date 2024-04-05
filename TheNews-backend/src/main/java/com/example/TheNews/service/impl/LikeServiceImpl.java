package com.example.TheNews.service.impl;

import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.LikeRepo;
import com.example.TheNews.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepo likeRepo;

    public List<LikeEntity> getLikesByArticleId(long article_l) {
        // Загружаем лайки из базы данных
        return likeRepo.findByArticleId(article_l);
    }

    public void createLike(long user_l, long article_l) {
        // Создаем лайк
        LikeEntity like = new LikeEntity();

        // Устанавливаем автора, текст, дату публикации коммента
        like.setUser_l(user_l);
        like.setArticle_l(article_l);
        // Сохраняем статью в базе данных
        likeRepo.save(like);
    }

    public Long delete(Long like_id) {
        likeRepo.deleteById(like_id);
        return like_id;
    }
}
