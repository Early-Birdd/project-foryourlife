package com.example.projectforyourlife.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberNicknameUpdateDto {

    @Id
    private Long memberId;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Length(min = 2, max = 10, message = "2자리 이상 10자리 이하의 닉네임을 입력해주세요.")
    private String nickname;
}
