package com.example.TheNews.controller;

import com.example.TheNews.dto.request.LikeDto;
import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.repository.LikeRepo;
import com.example.TheNews.service.LikeService;
import com.example.TheNews.service.facade.LikeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;

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

    //ок
    @PostMapping("/auth/likes/likeNum")
    public ResponseEntity<?> likeNum(@RequestParam long article_id) {
        try {
            return ResponseEntity.ok(likeFacade.likeNumFacade(article_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    //ок
    @PostMapping("/auth/likes/isLiked")
    public ResponseEntity<?> isLiked(@RequestParam long user_id, @RequestParam long article_id) {
        try {
            return ResponseEntity.ok(likeFacade.isLikedBy(user_id, article_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    //ок
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

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @DeleteMapping("/user/likes/{userL}/{articleL}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteLike(@PathVariable long userL,
                                        @PathVariable long articleL) {
        try {
            LikeDto likeDto = new LikeDto();
            likeDto.setUserL(userL);
            likeDto.setArticleL(articleL);
            likeFacade.deleteLikeFacade(likeDto);
            return ResponseEntity.ok("Лайк успешно удален");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @GetMapping ("/admin/likes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllLikes() {
        List<LikeEntity> likes = likeRepo.findAll();
        return ResponseEntity.ok(likes);
    }
}
