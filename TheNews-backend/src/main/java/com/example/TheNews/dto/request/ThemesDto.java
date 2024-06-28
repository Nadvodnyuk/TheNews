package com.example.TheNews.dto.request;

import java.util.List;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemesDto {
    private List<String> favorite;
    private List<String> disliked;
}
