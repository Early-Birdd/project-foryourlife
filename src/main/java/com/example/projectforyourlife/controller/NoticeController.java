package com.example.projectforyourlife.controller;

import com.example.projectforyourlife.dto.request.NoticeDto;
import com.example.projectforyourlife.dto.response.NoticeListResponseDto;
import com.example.projectforyourlife.dto.response.NoticeResponseDto;
import com.example.projectforyourlife.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/create")
    public ResponseEntity<NoticeResponseDto> save(@RequestBody NoticeDto noticeDto){

        //Constructor 스타일
        return new ResponseEntity(noticeService.saveNotice(noticeDto), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<NoticeListResponseDto>> getAll(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        return new ResponseEntity(noticeService.getAllNotice(pageable).getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponseDto> getDetail(@PathVariable("id") Long id){

        return new ResponseEntity(noticeService.getDetailNotice(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable("id") Long id, @RequestBody NoticeDto noticeUpdateDto){

        return new ResponseEntity(noticeService.updateNotice(id, noticeUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){

        noticeService.deleteNotice(id);

        //204
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
