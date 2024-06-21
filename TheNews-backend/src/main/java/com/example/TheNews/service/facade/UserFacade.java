package com.example.TheNews.service.facade;

import com.example.TheNews.dto.request.DeleteUserDto;
import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.response.SignInResponseDto;
import com.example.TheNews.exception.NotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Service;

@Service
public interface UserFacade {
    //Рега
    void registerFacade(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam String username,
                        @RequestParam String password) throws NotFoundException;

    //Вход
    SignInResponseDto authenticateFacade(@RequestBody SignInDto loginUserDto) throws NotFoundException;

    boolean authenticatedUserFacade();

    //Выход
    void logOutFacade(HttpServletRequest request, HttpServletResponse response);

    long deleteFacade(long user_id);
}
