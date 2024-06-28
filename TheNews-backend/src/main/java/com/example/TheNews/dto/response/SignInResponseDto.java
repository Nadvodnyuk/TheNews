package com.example.TheNews.dto.response;

import com.example.TheNews.entity.Role;
import com.example.TheNews.entity.Theme;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
    private long user_id;
    private String name;
    private Role role;
    private String token;
    private long expiresIn;
    private Set<Theme> favoriteTopics;
    private Set<Theme> blockedTopics;
}
