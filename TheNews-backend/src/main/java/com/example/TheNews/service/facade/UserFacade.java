package com.example.TheNews.service.facade;

import com.example.TheNews.dto.request.DeleteUserDto;
import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.response.SignInResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Service;
@Service
public interface UserFacade {
    //Рега
    public void registerFacade(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String username,
                               @RequestParam String password);

    //Вход
    public void authenticateFacade(@RequestBody SignInDto loginUserDto);

    public void authenticatedUserFacade();

    //Выход
    public ResponseEntity<?> logOutFacade(HttpServletRequest request);

    public long deleteFacade(DeleteUserDto user);
}
