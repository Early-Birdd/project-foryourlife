package com.example.projectforyourlife.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberNameUpdateDto {

    @Id
    private Long memberId;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
}
