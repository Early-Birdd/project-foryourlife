package com.example.projectforyourlife.dto.request;

import com.example.projectforyourlife.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeUpdateDto {

    @Id
    private Long id;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    public Notice toUpdateNotice(){

        return Notice.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
