package com.example.TheNews.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.TheNews.entity.TopicEntity;
import com.example.TheNews.exception.AlreadyExistException;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.TopicRepo;
import org.springframework.stereotype.Service;

@Service

public interface TopicService {
    public TopicEntity createTopic(TopicEntity name) throws AlreadyExistException;
    public Long delete(Long topic_id);
}
