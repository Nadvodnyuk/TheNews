package com.example.TheNews.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCommentDto {
    private long comment_id;
}