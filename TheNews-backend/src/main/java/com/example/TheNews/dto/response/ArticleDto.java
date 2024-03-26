package com.example.TheNews.dto.response;

import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.entity.TopicEntity;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDto {
    private long article_id;
    private String title;
    private String article_text;
    private String image_url;
    private Integer like_num;
    private Integer comment_num;
    private java.sql.Timestamp publication_date;
    private List<LikeEntity> likes;
    private List<CommentEntity> comments;
    private List<TopicEntity> topics;
}