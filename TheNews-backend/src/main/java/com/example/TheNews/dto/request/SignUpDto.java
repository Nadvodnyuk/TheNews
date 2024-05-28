package com.example.TheNews.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String first_name;
    private String last_name;
    private String username;
    private String password;

}
