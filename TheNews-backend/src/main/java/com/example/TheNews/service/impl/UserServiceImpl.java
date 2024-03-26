package com.example.TheNews.service.impl;

import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.AlreadyExistException;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.UserRepo;
import com.example.TheNews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

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
