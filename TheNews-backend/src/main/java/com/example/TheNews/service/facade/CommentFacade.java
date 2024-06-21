package com.example.TheNews.service.facade;

import com.example.TheNews.dto.request.CreateCommentDto;
import com.example.TheNews.dto.response.CommentDto;
import com.example.TheNews.exception.NotFoundException;

import java.util.List;
import org.springframework.stereotype.Service;
@Service
public interface CommentFacade {

    int commentNumFacade(long article_id);

    //Вывести все комменты к статье
    List<CommentDto> getCommentsByArticleIdWithPaginationFacade(long article_c, int page)
            throws NotFoundException;

    //Написать коммент
    void createCommentFacade(long user_c, CreateCommentDto comment, long article_с)
            throws NotFoundException;

    //Удалить коммент
    void deleteFacade(long comment_id);

}
