package com.example.TheNews.dto.response;

import lombok.Data;

import java.sql.Timestamp;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long comment_id;
    private String comment_text;
    private java.sql.Timestamp comment_date;
}

