package com.example.TheNews.dto.request;

import com.example.TheNews.entity.Theme;
import lombok.*;

import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditArticleDto {
    private long article_id;
    private String title;
    private String article_text;
    private String image_url;
    private Set<Theme> topics;
}
