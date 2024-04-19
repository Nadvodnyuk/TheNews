package com.example.TheNews.dto.request;

import com.example.TheNews.entity.TopicEntity;

import lombok.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticleDto {
    private String title;
    private String article_text;
    private String image_url;
    private Set<TopicEntity> topics;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle_text() {
        return article_text;
    }

    public void setArticle_text(String article_text) {
        this.article_text = article_text;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Set<TopicEntity> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicEntity> topics) {
        this.topics = topics;
    }
}