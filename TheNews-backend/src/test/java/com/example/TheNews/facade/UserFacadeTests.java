 package com.example.TheNews.facade;

import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.UserRepo;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.UserService;
import com.example.TheNews.service.facade.impl.UserFacadeImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

import static com.example.TheNews.entity.Role.ROLE_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserFacadeTests {

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserFacadeImpl userFacade;

    @Mock
    private UserRepo userRepo;

    @Mock
    private AuthenticationManager authenticationManager;

    UserEntity user = UserEntity.builder()
            .user_id(1)
            .first_name("Yana")
            .last_name("Mark")
            .role(ROLE_USER)
            .username("Team")
            .password("222")
            .createdAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
            .updatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now())).build();

    @Test
    public void testAuthenticateFacade() throws NotFoundException {
        UserEntity authenticatedUser = new UserEntity();
        when(userService.authenticate(any(SignInDto.class))).thenReturn(authenticatedUser);

        String jwtToken = "your_generated_jwt_token";

        when(jwtService.generateToken(any(UserEntity.class))).thenReturn(jwtToken);

        long time = 3600000L;
        when(jwtService.getExpirationTime()).thenReturn(time);

        userFacade.authenticateFacade(new SignInDto());

        verify(userService).authenticate(any(SignInDto.class));
        verify(jwtService).generateToken(any(UserEntity.class));
        verify(jwtService).getExpirationTime();
    }

    @Test
    public void UserFacade_authenticatedUserFacade_ReturnsVoid() {

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserFacadeImpl userFacade = new UserFacadeImpl();

        userFacade.authenticatedUserFacade();

        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        assertEquals(user.getUsername(), currentUser.getUsername());
        assertEquals(user.getUser_id(), currentUser.getUser_id());
    }

    @Test
    void logOutFacade_WithValidToken_ShouldInvalidateToken() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer valid_token");

        userFacade.logOutFacade(request);

        verify(jwtService).invalidateToken("valid_token");
    }

    @Test
    void logOutFacade_WithNoToken_ShouldNotInvalidateToken() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn(null);

        userFacade.logOutFacade(request);

        verifyNoInteractions(jwtService);
    }

    @Test
    void logOutFacade_WithInvalidTokenFormat_ShouldNotInvalidateToken() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("invalid_format");

        userFacade.logOutFacade(request);

        verifyNoInteractions(jwtService);
    }
}