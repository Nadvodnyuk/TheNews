package com.example.TheNews.service.impl;

import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.AlreadyExistException;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.UserRepo;
import com.example.TheNews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String firstName, String lastName, String username, String password) {
        // Проверяем, существует ли пользователь с таким именем пользователя
        if (userRepo.findByUsername(username) != null) {
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
        user.setRole("USER");

        // Сохраняем пользователя в базе данных
        userRepo.save(user);
    }

    //Регистрация
    public UserEntity registration(UserEntity username) throws AlreadyExistException {
        if (userRepo.findByUsername(username.getUsername()) != null) {
            throw new AlreadyExistException("Пользователь с таким именем существует");
        }
        return userRepo.save(username);
    }


    public Long delete(Long user_id) {
        userRepo.deleteById(user_id);
        return user_id;
    }
}
