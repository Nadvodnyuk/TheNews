package com.example.TheNews.service;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.LikeEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.repository.LikeRepo;
import com.example.TheNews.service.impl.LikeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.TheNews.entity.Role.ROLE_USER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LikeServiceTests {
    @Mock
    private LikeRepo likeRepo;

    @InjectMocks
    private LikeServiceImpl likeService;

    UserEntity user = UserEntity.builder()
            .user_id(1)
            .first_name("Yana")
            .last_name("Mark")
            .role(ROLE_USER)
            .username("Team")
            .password("222")
            .createdAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
            .updatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now())).build();

    ArticleEntity article = ArticleEntity.builder()
            .article_id(1)
            .title("The Article")
            .article_text("This id the article we've created")
            .like_num(2)
            .comment_num(1)
            .publicationDate(java.sql.Timestamp.valueOf(LocalDateTime.now()))
            .userA(user).build();

    LikeEntity like = LikeEntity.builder()
            .like_id(1)
            .userL(user)
            .articleL(article).build();

    @Test
    public void LikeService_getLikesByArticleId_ReturnsInt() {
        //сомневаюсь максимально
        List<LikeEntity> likeNum = Mockito.mock(List.class);

        when(likeRepo.findByArticleL(article)).thenReturn(likeNum);

        int saveLike = likeService.getLikesByArticleId(article);

        Assertions.assertThat(saveLike).isNotNull();
    }

    @Test
    public void LikeService_isLikedByUserAndArticle_ReturnsBoolean() {
        List<LikeEntity> isLiked = Mockito.mock(List.class);

        when(likeRepo.findByArticleLAndUserL(article, user))
                .thenReturn(isLiked);

        boolean saveLike = likeService.isLikedByUserAndArticle(user, article);

        Assertions.assertThat(saveLike).isTrue();
    }

    @Test
    public void LikeService_CreateLike_ReturnsVoid() {

        when(likeRepo.save(Mockito.any(LikeEntity.class))).thenReturn(like);

        assertAll(() -> likeService.createLike(like.getUserL(), like.getArticleL()));
    }

    @Test
    public void LikeService_delete_ReturnsVoid() {

//        when(likeRepo.deleteByArticleLAndUserL(article, user)).thenReturn(Optional.ofNullable(like));
        doNothing().when(likeRepo).deleteByArticleLAndUserL(like.getArticleL(), like.getUserL());

        assertAll(() -> likeService.delete(like.getUserL(), like.getArticleL()));
    }

}
