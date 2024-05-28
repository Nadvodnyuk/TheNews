package com.example.TheNews.service;


import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.AlreadyExistException;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface UserService {
    UserEntity registerUser(String firstName, String lastName, String username, String password);

    UserEntity authenticate(SignInDto input) throws RuntimeException;

    UserEntity getOne(Long art_id) throws NotFoundException;

    List<UserEntity> allUsers();

    long delete(Long user_id);

}
