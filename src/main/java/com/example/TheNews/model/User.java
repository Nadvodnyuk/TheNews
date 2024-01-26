package com.example.TheNews.model;

import com.example.TheNews.entity.UserEntity;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private long user_id;
	private String first_name;
	private String last_name;
	private String role;
	private String username;
    private List<Like> likes;
    private List<Article> articles;
    private List<Comment> comments;

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setUser_id(entity.getUser_id());
        model.setFirst_name(entity.getFirst_name());
        model.setLast_name(entity.getLast_name());
        model.setRole(entity.getRole());
        model.setUsername(entity.getUsername());
        model.setLikes(entity.getLikes().stream().map(Like::toModel).collect(Collectors.toList()));
        model.setArticles(entity.getArticles().stream().map(Article::toModel).collect(Collectors.toList()));
        model.setComments(entity.getComments().stream().map(Comment::toModel).collect(Collectors.toList()));
        return model;
    }

    public User() {
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}