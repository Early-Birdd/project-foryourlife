package com.example.projectforyourlife.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfoDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;

    private Long accessTokenExpiresIn;
}
