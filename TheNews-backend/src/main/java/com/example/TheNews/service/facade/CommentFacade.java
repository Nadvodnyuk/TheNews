package com.example.TheNews.service.facade;

import com.example.TheNews.dto.request.CreateCommentDto;
import com.example.TheNews.dto.request.DeleteCommentDto;
import com.example.TheNews.dto.response.CommentDto;
import com.example.TheNews.exception.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentFacade {

    //Вывести все комменты к статье
    public List<CommentDto> getCommentsByArticleIdWithPaginationFacade(long article_c, int page)
            throws NotFoundException;

    //Написать коммент
    public void createCommentFacade(long user_c, CreateCommentDto comment, long article_с)
            throws NotFoundException;

    //Удалить коммент
    public void deleteFacade(long comment_id);

}
