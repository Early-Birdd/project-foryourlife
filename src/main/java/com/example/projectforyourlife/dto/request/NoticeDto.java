package com.example.projectforyourlife.dto.request;

import com.example.projectforyourlife.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private String writer;

    public Notice toNotice(){

        return Notice.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
