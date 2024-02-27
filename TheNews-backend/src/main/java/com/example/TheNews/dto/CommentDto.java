package com.example.TheNews.dto;

import lombok.*;

@Data
public class CommentDto {
    private long comment_id;
    private String comment_text;
    private java.sql.Timestamp comment_date;
    
}
