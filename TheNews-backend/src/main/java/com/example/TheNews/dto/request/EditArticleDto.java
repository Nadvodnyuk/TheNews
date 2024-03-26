package com.example.TheNews.dto.request;

import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.entity.TopicEntity;
import lombok.*;

import java.util.List;
@Data
public class EditArticleDto {
    private long article_id;
    private String title;
    private String article_text;
    private String image_url;
    private List<TopicEntity> topics;
}
