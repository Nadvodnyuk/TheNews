package com.example.TheNews.service.facade;

import com.example.TheNews.dto.response.ThemeDto;
import com.example.TheNews.entity.Theme;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ThemeFacade {
    ThemeDto getAllThemesFacade();
}
