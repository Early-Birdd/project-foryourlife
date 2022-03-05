package com.example.projectforyourlife.service;

import com.example.projectforyourlife.dto.request.NoticeDto;
import com.example.projectforyourlife.dto.response.NoticeListResponseDto;
import com.example.projectforyourlife.dto.response.NoticeResponseDto;
import com.example.projectforyourlife.entity.Notice;
import com.example.projectforyourlife.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public NoticeResponseDto saveNotice(NoticeDto noticeDto){

        return NoticeResponseDto.show(noticeRepository.save(noticeDto.toNotice()));
    }

    public Page<NoticeListResponseDto> getAllNotice(Pageable pageable){

        Page<Notice> notices = noticeRepository.findAll(pageable);

        Page<NoticeListResponseDto> noticeListResponseDtos = notices.map(
                notice -> new NoticeListResponseDto(notice.getId(), notice.getTitle(), notice.getWriter(),
                        notice.getCreatedTime(), notice.getModifiedTime()));

        return noticeListResponseDtos;
    }

    public NoticeResponseDto getDetailNotice(Long id){

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("공지사항이 존재하지 않습니다"));

        return NoticeResponseDto.show(notice);
    }

    @Transactional
    public Long updateNotice(Long id, NoticeDto noticeUpdateDto){

        Notice notice = noticeRepository.findById(id)
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
