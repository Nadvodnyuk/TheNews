package com.example.TheNews.dto.response;

import com.example.TheNews.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDto {
    private List<Theme> themes;
}