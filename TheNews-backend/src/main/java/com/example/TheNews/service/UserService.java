package com.example.TheNews.service;


import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.entity.Theme;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service

public interface UserService {
    UserEntity registerUser(String firstName, String lastName, String username, String password);

    UserEntity authenticate(SignInDto input) throws RuntimeException;

    UserEntity getOne(Long art_id) throws NotFoundException;

    List<UserEntity> allUsers();

    long delete(Long user_id);

    void postThemesService(Set<Theme> favoriteThemes, Set<Theme> dislikedThemes, Authentication authentication);
}
