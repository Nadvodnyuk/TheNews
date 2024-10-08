 package com.example.TheNews.facade;

import com.example.TheNews.dto.request.SignInDto;
import com.example.TheNews.dto.response.SignInResponseDto;
import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.exception.NotFoundException;
import com.example.TheNews.repository.UserRepo;
import com.example.TheNews.service.JwtService;
import com.example.TheNews.service.UserService;
import com.example.TheNews.service.facade.impl.UserFacadeImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import static org.junit.jupiter.api.Assertions.*;
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
        UserEntity authenticatedUser = UserEntity.builder().build();
        when(userService.authenticate(any(SignInDto.class))).thenReturn(authenticatedUser);

        String jwtToken = "your_generated_jwt_token";
        when(jwtService.generateToken(authenticatedUser)).thenReturn(jwtToken);

        long time = 3600000L;
        when(jwtService.getExpirationTime()).thenReturn(time);

        SignInResponseDto signInResponseDto = userFacade.authenticateFacade(new SignInDto());

        assertNotNull(signInResponseDto);
        assertEquals(authenticatedUser.getUser_id(), signInResponseDto.getUser_id());
        assertEquals(authenticatedUser.getFirst_name() + " " + authenticatedUser.getLast_name(), signInResponseDto.getName());
        assertEquals(authenticatedUser.getRole(), signInResponseDto.getRole());
        assertEquals(jwtToken, signInResponseDto.getToken());
        assertEquals(time, signInResponseDto.getExpiresIn());

        verify(userService).authenticate(any(SignInDto.class));
        verify(jwtService).generateToken(authenticatedUser);
        verify(jwtService).getExpirationTime();
    }

    @Test
    public void testAuthenticatedUserFacade_AuthenticatedUser() {
        UserEntity user = UserEntity.builder()
                .username("username")
                .build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean isAuthenticated = userFacade.authenticatedUserFacade();

        assertTrue(isAuthenticated);
    }

    @Test
    public void testAuthenticatedUserFacade_AnonymousUser() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn("anonymousUser");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean isAuthenticated = userFacade.authenticatedUserFacade();

        assertFalse(isAuthenticated);
    }

    @Test
    public void testLogOutFacade_WithValidToken() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer valid_token");

        userFacade.logOutFacade(request, response);

        verify(jwtService).invalidateToken("valid_token");
    }

    @Test
    public void testLogOutFacade_WithNoToken() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getHeader("Authorization")).thenReturn(null);

        userFacade.logOutFacade(request, response);

        verifyNoInteractions(jwtService);
    }

    @Test
    public void testLogOutFacade_WithInvalidTokenFormat() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getHeader("Authorization")).thenReturn("invalid_format");

        userFacade.logOutFacade(request, response);

        verifyNoInteractions(jwtService);
    }
}