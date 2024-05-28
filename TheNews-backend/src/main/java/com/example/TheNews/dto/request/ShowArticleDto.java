package com.example.TheNews.dto.request;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowArticleDto {
    private java.sql.Timestamp publicationDate;
}
