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

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public UserEntity getUserC() {
        return userC;
    }

    public void setUserC(UserEntity userC) {
        this.userC = userC;
    }
}
