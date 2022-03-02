package com.example.projectforyourlife.dto.response;

import com.example.projectforyourlife.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {

    private Long id;

    private String email;
    private String name;
    private String nickname;

    public static MemberResponseDto show(Member member){

        return new MemberResponseDto(member.getId(), member.getEmail(), member.getName(), member.getNickname());
    }
}
