package com.example.TheNews.dto;

import com.example.TheNews.model.Comment;
import com.example.TheNews.model.Like;
import com.example.TheNews.model.Topic;
import lombok.*;
import java.util.List;

@Data
public class ArticleDtoOld {
    private long article_id;
    private String title;
    private String article_text;
    private String image_url;
    private Integer like_num;
    private Integer comment_num;
    private java.sql.Timestamp publication_date;
    private List<Like> likes;
    private List<Comment> comments;
    private List<Topic> topics;
}
