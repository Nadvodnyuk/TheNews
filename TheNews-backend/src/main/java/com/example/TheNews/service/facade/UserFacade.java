package com.example.TheNews.service.facade;

import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.response.SignInResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserFacade {
//Рега
public ResponseEntity<?> registerFacade(@RequestParam String firstName,
                                        @RequestParam String lastName,
                                        @RequestParam String username,
                                        @RequestParam String password);
//Вход
public ResponseEntity<?> authenticateFacade(@RequestBody SignInDto loginUserDto);
public ResponseEntity<?> authenticatedUserFacade();
//Выход
public ResponseEntity<?> logOutFacade(HttpServletRequest request);
}
