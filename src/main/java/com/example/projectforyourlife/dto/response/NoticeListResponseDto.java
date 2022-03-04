package com.example.projectforyourlife.dto.response;

import com.example.projectforyourlife.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeListResponseDto {

    private Long id;

    private String title;
    private String writer;

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
}
