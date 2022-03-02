package com.example.projectforyourlife.dto.response;

import com.example.projectforyourlife.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponseDto {

    private Long id;

    private String title;
    private String content;
    private String writer;

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public static NoticeResponseDto show(Notice notice){

        return  new NoticeResponseDto(notice.getId(), notice.getTitle(), notice.getContent(), notice.getWriter()
                , notice.getCreatedTime(), notice.getModifiedTime());
    }
}
