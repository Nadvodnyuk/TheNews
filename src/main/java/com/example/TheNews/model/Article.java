package com.example.TheNews.model;

import com.example.TheNews.entity.ArticleEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class Article {
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

    public static Article toModel(ArticleEntity entity) {
        Article model = new Article();

        model.setArticle_id(entity.getArticle_id());
        model.setTitle(entity.getTitle());
        model.setArticle_text(entity.getArticle_text());
        model.setImage_url(entity.getImage_url());
        model.setLike_num(entity.getLike_num());
        model.setComment_num(entity.getComment_num());
        model.setPublication_date(entity.getPublication_date());
        model.setLikes(entity.getLikes().stream().map(Like::toModel).collect(Collectors.toList()));
        model.setComments(entity.getComments().stream().map(Comment::toModel).collect(Collectors.toList()));
        model.setTopics(entity.getTopics().stream().map(Topic::toModel).collect(Collectors.toList()));
        return model;
    }

    public Article() {
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(long article_id) {
        this.article_id = article_id;
    }

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

    public Integer getLike_num() {
        return like_num;
    }

    public void setLike_num(Integer like_num) {
        this.like_num = like_num;
    }

    public Integer getComment_num() {
        return comment_num;
    }

    public void setComment_num(Integer comment_num) {
        this.comment_num = comment_num;
    }

    public Timestamp getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Timestamp publication_date) {
        this.publication_date = publication_date;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
