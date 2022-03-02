package com.example.projectforyourlife.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LogoutDto {

    private String accessToken;
    private String refreshToken;
}
