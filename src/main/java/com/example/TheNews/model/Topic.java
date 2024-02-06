package com.example.TheNews.model;

import com.example.TheNews.entity.TopicEntity;

public class Topic {
    private long topic_id;
    private String name;
    // private List<Article> articles;

    public static Topic toModel(TopicEntity entity) {
        Topic model = new Topic();
        model.setTopic_id(entity.getTopic_id());
        model.setName(entity.getName());
        // model.setArticles(entity.getArticles().stream().map(Article::toModel).collect(Collectors.toList()));
        return model;
    }

    public Topic() {
    }

    public long getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(long topic_id) {
        this.topic_id = topic_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ниже неправильно, надо будет потом пересмотреть, потому что я не уверена, что так работает с many to many

    // public List<Article> getArticles() {
    // return articles;
    // }
    // public void setArticles(List<Article> articles) {
    // this.articles = articles;
    // }
}
