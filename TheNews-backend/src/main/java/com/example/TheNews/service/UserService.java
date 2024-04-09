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
    public UserEntity registerUser(String firstName, String lastName, String username, String password);

    public UserEntity authenticate(SignInDto input);

    public UserEntity getOne(Long art_id) throws NotFoundException;

    public List<UserEntity> allUsers();

    public Long deleteToken(Long user_id);

    public long delete(Long user_id);

}
