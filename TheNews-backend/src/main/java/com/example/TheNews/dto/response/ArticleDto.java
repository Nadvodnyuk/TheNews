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
    private java.sql.Timestamp publicationDate;
    private String topics;

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }
}