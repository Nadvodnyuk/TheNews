package com.example.TheNews.service.facade;

import com.example.TheNews.dto.response.ThemeDto;
import org.springframework.stereotype.Service;

@Service
public interface ThemeFacade {
    ThemeDto getAllThemesFacade();
}
