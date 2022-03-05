package com.example.projectforyourlife.dto.request;

import com.example.projectforyourlife.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

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
    @Length(min = 8, max = 12, message = "8자리 이상 12자리 이하의 비밀번호를 입력해주세요")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Length(min = 2, max = 10, message = "2자리 이상 10자리 이하의 닉네임을 입력해주세요.")
    private String nickname;

    public Member toMember(){

        //MemberDto 를 Member에 빌딩하고 빌딩된 Member를 Service단에서 Repository에 저장 후 MemberResponseDto로 show
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .build();
    }
}
