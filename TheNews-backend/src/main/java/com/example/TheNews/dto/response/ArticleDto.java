package com.example.TheNews.dto.response;

import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.entity.LikeEntity;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private long article_id;
    private String title;
    private String article_text;
    private String image_url;
    private Integer like_num;
    private Integer comment_num;
    private java.sql.Timestamp publication_date;
    private String topics;

    public Timestamp getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Timestamp publication_date) {
        this.publication_date = publication_date;
    }
}