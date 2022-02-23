package com.example.projectforyourlife.dto;

import com.example.projectforyourlife.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//회원가입 화면에서 넘어오는 정보
//유효성 검사 -> spring-boot-starter-validation
//controller @valid에서 검증 예정
@Getter
@NoArgsConstructor
@AllArgsConstructor //생성자 공부하기
public class MemberDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    public Member toMember(){

        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .build();
    }
}
