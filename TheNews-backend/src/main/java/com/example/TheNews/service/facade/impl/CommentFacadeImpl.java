package com.example.TheNews.service.facade.impl;

import com.example.TheNews.dto.request.DeleteCommentDto;
import com.example.TheNews.dto.request.DeleteUserDto;
import com.example.TheNews.entity.ArticleEntity;
import com.example.TheNews.entity.CommentEntity;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.service.CommentService;
import com.example.TheNews.service.ArticleService;
import com.example.TheNews.repository.CommentRepo;
import com.example.TheNews.service.UserService;
import com.example.TheNews.service.facade.CommentFacade;
import com.example.TheNews.dto.request.CreateCommentDto;
import com.example.TheNews.dto.response.CommentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Type;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class CommentFacadeImpl implements CommentFacade {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    //Вывести все комменты к статье
    //С.Вывести все комменты к статье
    //Я не уверена, работает ли
    public List<CommentDto> getCommentsByArticleIdWithPaginationFacade(long article_c, int page) throws NotFoundException {
        ArticleEntity art = articleService.getOne(article_c);
        List<CommentEntity> threeCommentsEntity = commentService.getCommentsByArticleIdWithPagination(art, page);
        // Create Conversion Type
        Type listType = new TypeToken<List<CommentDto>>() {
        }.getType();
        // Convert List of Entity objects to a List of DTOs objects
        List<CommentDto> threeComments = new ModelMapper().map(threeCommentsEntity, listType);
        return threeComments;
    }

    //Написать коммент к статье
    //С.Написать коммент
    public void createCommentFacade(long user_c, CreateCommentDto comment, long article_с) throws NotFoundException {
        ArticleEntity art = articleService.getOne(article_с);
        UserEntity user = userService.getOne(user_c);
        commentService.createComment(user, comment.getComment_text(), art);
    }

    //Удалить коммент для админа
    //С.Удалить комм
    public void deleteFacade(long comment_id) {
        commentService.delete(comment_id);
    }
}
