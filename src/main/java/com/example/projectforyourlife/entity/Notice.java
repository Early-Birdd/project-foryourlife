package com.example.projectforyourlife.entity;

import com.example.projectforyourlife.dto.request.NoticeUpdateDto;
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

    private String writer;

    public void update(NoticeUpdateDto noticeUpdateDto){

        this.title = noticeUpdateDto.getTitle();
        this.content = noticeUpdateDto.getContent();
    }
}
