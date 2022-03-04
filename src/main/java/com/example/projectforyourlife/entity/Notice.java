package com.example.projectforyourlife.entity;

import com.example.projectforyourlife.dto.request.NoticeDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notice extends BaseTimeEntity{

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String writer;

    public void update(NoticeDto noticeUpdateDto){

        this.title = noticeUpdateDto.getTitle();
        this.content = noticeUpdateDto.getContent();
    }
}
