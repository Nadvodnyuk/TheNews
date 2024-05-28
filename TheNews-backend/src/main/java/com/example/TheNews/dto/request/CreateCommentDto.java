package com.example.TheNews.dto.request;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentDto {
    private String comment_text;
}
