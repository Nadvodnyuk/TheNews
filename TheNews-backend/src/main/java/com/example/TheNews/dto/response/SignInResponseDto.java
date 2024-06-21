package com.example.TheNews.dto.response;

import com.example.TheNews.entity.Role;
import lombok.*;

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
}
