package com.SchoolManagementSys.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto
{
    private Long userId;
    private String accessToken;
    private String refreshToken;
}
