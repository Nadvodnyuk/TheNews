package com.example.TheNews.dto.request;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.UserEntity;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {
    private long userL;
    private long articleL;

    public long getUserL() {
        return userL;
    }

    public void setUserL(long userL) {
        this.userL = userL;
    }

    public long getArticleL() {
        return articleL;
    }

    public void setArticleL(long articleL) {
        this.articleL = articleL;
    }
}
