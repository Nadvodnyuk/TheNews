package com.example.TheNews.repository;

import com.example.TheNews.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

}