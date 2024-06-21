package com.example.TheNews.controller;

import com.example.TheNews.dto.request.LikeDto;
import com.example.TheNews.repository.LikeRepo;
import com.example.TheNews.service.LikeService;
import com.example.TheNews.service.facade.LikeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class LikeController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LikeFacade likeFacade;

    @Autowired
    private LikeService likeService;

    @Autowired
    private LikeRepo likeRepo;


    @PostMapping("/auth/likes/likeNum")
    public ResponseEntity<?> likeNum(@RequestParam long article_id) {
        try {
            return ResponseEntity.ok(likeFacade.likeNumFacade(article_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


    @PostMapping("/auth/likes/isLiked")
    public ResponseEntity<?> isLiked(@RequestParam long user_id, @RequestParam long article_id) {
        try {
            return ResponseEntity.ok(likeFacade.isLikedBy(user_id, article_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


    @PostMapping("/user/likes/postLike")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> postLike(@RequestBody LikeDto likeDto) {
        try {
            likeFacade.putLikeFacade(likeDto);
            return ResponseEntity.ok("Лайк успешно поставлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


    @DeleteMapping("/user/likes/{userL}/{articleL}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteLike(@PathVariable long userL,
                                        @PathVariable long articleL) {
        try {
            likeFacade.deleteLikeFacade(userL, articleL);
            return ResponseEntity.ok("Лайк успешно удален");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
