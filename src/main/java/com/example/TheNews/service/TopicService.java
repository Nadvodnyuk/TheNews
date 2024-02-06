package com.example.TheNews.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.TheNews.model.Topic;
import com.example.TheNews.entity.TopicEntity;
import com.example.TheNews.exception.UserAlreadyExistException;
import com.example.TheNews.exception.UserNotFoundException;
import com.example.TheNews.repository.TopicRepo;
import org.springframework.stereotype.Service;

@Service

public class TopicService {
    @Autowired
    private TopicRepo topicRepo;

    public TopicEntity createTopic(TopicEntity name) throws UserAlreadyExistException {
        if (topicRepo.findByName(name.getName()) != null) {
            throw new UserAlreadyExistException("Такая тема уже существует");
        }
        return topicRepo.save(name);
    }

    public Topic getOne(String name) throws UserNotFoundException {
        TopicEntity topic = topicRepo.findByName(name);
        if (topic == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return Topic.toModel(topic);
    }

    public Long delete(Long topic_id) {
        topicRepo.deleteById(topic_id);
        return topic_id;
    }
}
