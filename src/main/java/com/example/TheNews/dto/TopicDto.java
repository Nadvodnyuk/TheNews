package com.example.TheNews.dto;

import com.example.TheNews.model.Article;
import lombok.*;

import java.util.List;

@Data
public class TopicDto {
    private long topic_id;
    private String name;
    private List<Article> articles;
}
