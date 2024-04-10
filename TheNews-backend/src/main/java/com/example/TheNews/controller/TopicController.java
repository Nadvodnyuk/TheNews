package com.example.TheNews.controller;

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

    // ВСТАВИТЬ DELETE MAPPING
    @DeleteMapping("/{topic_id}")
    public ResponseEntity deleteTopic(@PathVariable Long topic_id) {
        try {
            return ResponseEntity.ok(topicService.delete(topic_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
