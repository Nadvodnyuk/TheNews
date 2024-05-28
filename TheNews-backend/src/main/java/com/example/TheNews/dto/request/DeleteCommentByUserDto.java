package com.example.TheNews.dto.request;

import com.example.TheNews.entity.UserEntity;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCommentByUserDto {
    private long comment_id;
    private UserEntity userC;
}
