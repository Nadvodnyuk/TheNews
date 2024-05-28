package com.example.TheNews.dto.request;


import lombok.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticleDto {
    private String title;
    private String article_text;
    private String image_url;
    private String topics;
}