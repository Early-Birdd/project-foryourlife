package com.example.projectforyourlife.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Length(min = 10, max = 16, message = "10자리 이상 16자리 이하의 비밀번호를 입력해주세요")
    private String password;

    public UsernamePasswordAuthenticationToken toAuthentication(){

        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
