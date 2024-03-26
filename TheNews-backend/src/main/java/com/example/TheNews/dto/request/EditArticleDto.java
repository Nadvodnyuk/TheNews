package com.example.TheNews.dto.request;

import com.example.TheNews.model.Comment;
import com.example.TheNews.model.Like;
import com.example.TheNews.model.Topic;
import lombok.*;

import java.util.List;
@Data
public class EditArticleDto {
    private long article_id;
    private String title;
    private String article_text;
    private String image_url;
    private List<Topic> topics;
}
