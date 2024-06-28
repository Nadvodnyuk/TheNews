package com.example.TheNews.service.impl;

import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.entity.Theme;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.UserRepo;
import com.example.TheNews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.TheNews.entity.Role.ROLE_USER;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity registerUser(String firstName, String lastName, String username, String password) {
        // Проверяем, существует ли пользователь с таким именем пользователя
        if ((userRepo.findByUsername(username)).isPresent()) {
            throw new RuntimeException("Пользователь с таким именем пользователя уже существует");
        }

        // Создаем нового пользователя
        UserEntity user = new UserEntity();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setUsername(username);
        // Хешируем пароль перед сохранением в базу данных
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        // Устанавливаем роль по умолчанию (например, USER)
        user.setRole(ROLE_USER);

        // Сохраняем пользователя в базе данных
        return userRepo.save(user);
    }

    public UserEntity authenticate(SignInDto input) throws RuntimeException {
        try {
            UserEntity userUser = userRepo.findByUsername(input.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    input.getUsername(), input.getPassword(), userUser.getAuthorities());
            // Аутентификация пользователя
            authenticationManager.authenticate(authentication);

            // Установка аутентификации в контекст безопасности
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return userUser;
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Authentication failed: " + ex.getMessage());
        }
    }

    public List<UserEntity> allUsers() {
        return new ArrayList<>(userRepo.findAll());
    }

    public UserEntity getOne(Long art_id) throws NotFoundException {
        UserEntity art = userRepo.findById(art_id).get();
        if (art == null) {
            throw new NotFoundException("Пользователь не найден");
        }
        return art;
    }

    public long delete(Long user_id) {
        userRepo.deleteById(user_id);
        return (user_id);
    }

    @Override
    public void postThemesService(Set<Theme> favoriteThemes, Set<Theme> dislikedThemes, Authentication authentication) {
        String username = authentication.getName();
        Optional<UserEntity> userOptional = userRepo.findByUsername(username);

        UserEntity user = userOptional.get();
        user.setFavoriteTopics(favoriteThemes);
        user.setBlockedTopics(dislikedThemes);
        userRepo.save(user);
    }
}
