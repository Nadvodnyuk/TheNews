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
//	 private List<Like> likes;
//	 private List<Article> articles;
//	 private List<Comment> comments;

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setUser_id(entity.getId());
        model.setFirst_name(entity.getFirstName());
        model.setLast_name(entity.getLastName());
        model.setRole(entity.getRole());
        model.setUsername(entity.getUsername());
        // model.setTodos(entity.getTodos().stream().map(Todo::toModel).collect(Collectors.toList()));
        // model.setTodos(entity.getTodos().stream().map(Todo::toModel).collect(Collectors.toList()));
        // model.setTodos(entity.getTodos().stream().map(Todo::toModel).collect(Collectors.toList()));
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

    // public List<Todo> getTodos() {
    //     return todos;
    // }

    // public void setTodos(List<Todo> todos) {
    //     this.todos = todos;
    // }
}