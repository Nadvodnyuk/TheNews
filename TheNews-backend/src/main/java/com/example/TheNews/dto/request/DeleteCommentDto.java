package com.example.TheNews.dto.request;

import lombok.*;

@Data
public class DeleteCommentDto {
    private long comment_id;

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }
}