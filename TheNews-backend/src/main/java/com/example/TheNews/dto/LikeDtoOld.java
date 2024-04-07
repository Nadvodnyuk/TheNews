package com.example.TheNews.dto;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.UserEntity;
import lombok.*;

@Data
public class LikeDtoOld {
    private UserEntity userL;
    private ArticleEntity articleL;
}
