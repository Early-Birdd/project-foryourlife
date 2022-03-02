package com.example.projectforyourlife.service;

import com.example.projectforyourlife.dto.request.NoticeDto;
import com.example.projectforyourlife.dto.request.NoticeUpdateDto;
import com.example.projectforyourlife.dto.response.NoticeResponseDto;
import com.example.projectforyourlife.entity.Notice;
import com.example.projectforyourlife.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private NoticeRepository noticeRepository;

    @Transactional
    public NoticeResponseDto saveNotice(NoticeDto noticeDto){

        return NoticeResponseDto.show(noticeRepository.save(noticeDto.toNotice()));
    }

    public NoticeResponseDto readNotice(Long id){

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("공지사항이 존재하지 않습니다"));

        return NoticeResponseDto.show(notice);
    }

    @Transactional
    public Long updateNotice(NoticeUpdateDto noticeUpdateDto){

        Notice notice = noticeRepository.findById(noticeUpdateDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항은 존재하지 않습니다."));

        notice.update(noticeUpdateDto);

        return notice.getId();
    }

    public void deleteNotice(Long id){

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항은 존재하지 않습니다."));

        noticeRepository.delete(notice);
    }
}
