package com.example.projectforyourlife.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberListResponseDto {

    private Long id;

    private String email;
    private String name;
    private String nickname;
}
