package com.example.TheNews.service.impl;

import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.repository.CommentRepo;
import com.example.TheNews.service.CommentService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    //Для вывода комментов
    public List<CommentEntity> getCommentsByArticleIdWithPagination(ArticleEntity article_с, int page) {
        // Вычисляем номер страницы и количество элементов на странице
        int pageNumber = page - 1; // Страницы начинаются с 0
        PageRequest pageRequest = PageRequest.of(pageNumber, 3);

        // Загружаем комментарии из базы данных с учетом пагинации
        return commentRepo.findByArticleCOrderByCommentDateDesc(article_с, pageRequest);
    }

    public int getCommentsByArticleId(ArticleEntity article_c) {
        List<CommentEntity> likeNum = commentRepo.findByArticleC(article_c);
        return likeNum.size();
    }

    //Для создания коммента
    public void createComment(UserEntity user_c, String comment_text, ArticleEntity article_с) {
        // Создаем коммент
        CommentEntity comment = new CommentEntity();

        // Устанавливаем автора, текст, дату публикации коммента
        comment.setUserC(user_c);
        comment.setComment_text(comment_text);
        comment.setArticleC(article_с);
        java.sql.Timestamp currentDateTime = java.sql.Timestamp.valueOf(LocalDateTime.now());
        comment.setCommentDate(currentDateTime);

        // Сохраняем статью в базе данных
        commentRepo.save(comment);
    }

    //Для удаления коммента:

    public void delete(Long comment_id) {
        commentRepo.deleteById(comment_id);
    }
}
