package com.example.TheNews.service;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.repository.CommentRepo;
import com.example.TheNews.service.impl.CommentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.TheNews.entity.Role.ROLE_USER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTests {
    @Mock
    private CommentRepo commentRepo;

    @InjectMocks
    private CommentServiceImpl commentService;

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
            .article_text("This is the article we've created")
            .image_url("image")
            .like_num(2)
            .comment_num(1)
            .publicationDate(java.sql.Timestamp.valueOf(LocalDateTime.now()))
            .userA(user).build();

    CommentEntity comment = CommentEntity.builder()
            .comment_id(1)
            .comment_text("Wow!")
            .comment_date(java.sql.Timestamp.valueOf(LocalDateTime.now()))
            .userC(user)
            .articleC(article).build();

    @Test
    public void CommentService_isLikedByUserAndArticle_ReturnsList() {
        List<CommentEntity> comments = Mockito.mock(List.class);
        int page = 1;

        when(commentRepo.findByArticleC(article, PageRequest.of(page-1, 3))).thenReturn(comments);

        List<CommentEntity> threeComments = commentService.getCommentsByArticleIdWithPagination(article, page);

        Assertions.assertThat(threeComments).isNotNull();
    }

    @Test
    public void CommentService_CreateComment_ReturnsVoid() {

        when(commentRepo.save(Mockito.any(CommentEntity.class))).thenReturn(comment);

        assertAll(() -> commentService.createComment(comment.getUserC(),
                "WOW!",
                comment.getArticleC()));
    }

    @Test
    public void CommentService_delete_ReturnsVoid() {

        doNothing().when(commentRepo).deleteById(comment.getComment_id());

        assertAll(() -> commentService.delete(comment.getComment_id()));
    }
}
