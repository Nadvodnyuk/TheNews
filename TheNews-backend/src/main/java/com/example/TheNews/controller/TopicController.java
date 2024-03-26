package com.example.TheNews.controller;

import com.example.TheNews.dto.TopicDtoOld;
import com.example.TheNews.entity.TopicEntity;
import com.example.TheNews.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity createTopic(@RequestBody TopicEntity topic,
                                      @RequestBody TopicDtoOld topicDto) {
        try {
            TopicEntity topicRequest = modelMapper.map(topicDto, TopicEntity.class);
            TopicDtoOld topicResponse = modelMapper.map(
                    topicService.createTopic(topic), TopicDtoOld.class);
            return ResponseEntity.ok(topicResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка, лайк");
        }
    }

    // ВСТАВИТЬ DELETE MAPPING
    @DeleteMapping("/{topic_id}")
    public ResponseEntity deleteTopic(@PathVariable Long topic_id) {
        try {
            return ResponseEntity.ok(topicService.delete(topic_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
