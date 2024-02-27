package com.example.TheNews.dto;

import com.example.TheNews.model.Article;
import com.example.TheNews.model.Comment;
import com.example.TheNews.model.Like;
import lombok.*;

import java.util.List;

@Data
public class UserDto {
    private long user_id;
    private String first_name;
    private String last_name;
    private String role;
    private String username;
    private List<Like> likes;
    private List<Article> articles;
    private List<Comment> comments;
}
