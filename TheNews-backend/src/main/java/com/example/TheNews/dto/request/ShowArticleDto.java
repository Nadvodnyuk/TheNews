package com.example.TheNews.dto.request;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowArticleDto {
    private java.sql.Timestamp publicationDate;
}
