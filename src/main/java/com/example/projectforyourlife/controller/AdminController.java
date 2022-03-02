package com.example.projectforyourlife.controller;

import com.example.projectforyourlife.dto.request.NoticeDto;
import com.example.projectforyourlife.dto.request.NoticeUpdateDto;
import com.example.projectforyourlife.dto.response.NoticeResponseDto;
import com.example.projectforyourlife.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final NoticeService noticeService;

    @PostMapping("/notice/create")
    public ResponseEntity<NoticeResponseDto> save(@RequestBody NoticeDto noticeDto){

        return new ResponseEntity(noticeService.saveNotice(noticeDto), HttpStatus.OK);
    }

    @PutMapping("/notice/{id}")
    public ResponseEntity<NoticeUpdateDto> update(@RequestBody )
}
