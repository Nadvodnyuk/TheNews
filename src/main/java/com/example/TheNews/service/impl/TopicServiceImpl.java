package com.example.TheNews.service.impl;

import com.example.TheNews.entity.TopicEntity;
import com.example.TheNews.exception.AlreadyExistException;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.model.Topic;
import com.example.TheNews.service.TopicService;
import com.example.TheNews.repository.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepo topicRepo;

    public TopicEntity createTopic(TopicEntity name) throws AlreadyExistException {
        if (topicRepo.findByName(name.getName()) != null) {
            throw new AlreadyExistException("Такая тема уже существует");
        }
        return topicRepo.save(name);
    }

    public Topic getOne(String name) throws NotFoundException {
        TopicEntity topic = topicRepo.findByName(name);
        if (topic == null) {
            throw new NotFoundException("Пользователь не найден");
        }
        return Topic.toModel(topic);
    }

    public Long delete(Long topic_id) {
        topicRepo.deleteById(topic_id);
        return topic_id;
    }

}
