package com.example.TheNews.service.facade.impl;

import com.example.TheNews.dto.response.ThemeDto;
import com.example.TheNews.entity.Theme;
import com.example.TheNews.service.facade.ThemeFacade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ThemeFacadeImpl implements ThemeFacade {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ThemeDto getAllThemesFacade() {
        List<Theme> themes = Arrays.asList(Theme.values());
        return new ThemeDto(themes);
    }
}
