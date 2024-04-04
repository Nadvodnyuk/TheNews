package com.example.TheNews.service;

import com.example.TheNews.entity.LikeEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    public List<LikeEntity> getLikesByArticleId(long article_l);
    public void createLike(long user_l, long article_l);
    public Long delete(Long like_id);
}
