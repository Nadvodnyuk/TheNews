package com.example.TheNews.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserDto {
    private long user_id;
}
