package com.example.TheNews.model;

import com.example.TheNews.entity.CommentEntity;

import java.sql.Timestamp;

public class Comment {
    private long comment_id;
    private String comment_text;
    private java.sql.Timestamp comment_date;

    public static Comment toModel(CommentEntity entity) {
        Comment model = new Comment();
        model.setComment_id(entity.getComment_id());
        model.setComment_text(entity.getComment_text());
        model.setComment_date(entity.getComment_date());
        return model;
    }

    public Comment() {
    }

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Timestamp getComment_date() {
        return comment_date;
    }

    public void setComment_date(Timestamp comment_date) {
        this.comment_date = comment_date;
    }
}
