package com.example.TheNews.dto.request;

import lombok.Data;

@Data
public class SignUpDto {
    private String first_name;
    private String last_name;
    private String username;
    private String password;
}
