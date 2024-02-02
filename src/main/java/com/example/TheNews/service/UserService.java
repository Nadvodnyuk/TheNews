package com.example.TheNews.service;

import com.example.TheNews.model.User;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.UserAlreadyExistException;
import com.example.TheNews.exception.UserNotFoundException;
import com.example.TheNews.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity username) throws UserAlreadyExistException {
        if (userRepo.findByUsername(username.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем существует");
        }
        return userRepo.save(username);
    }

    public User getOne(Long user_id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(user_id).get();
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(user);
    }

    public Long delete(Long user_id) {
        userRepo.deleteById(user_id);
        return user_id;
    }
}
