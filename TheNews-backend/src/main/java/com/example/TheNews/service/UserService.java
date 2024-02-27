package com.example.TheNews.service;

import com.example.TheNews.model.User;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.AlreadyExistException;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public interface UserService {
    public UserEntity registration(UserEntity username) throws AlreadyExistException;
    public User getOne(Long user_id) throws NotFoundException;
    public Long delete(Long user_id);

}
