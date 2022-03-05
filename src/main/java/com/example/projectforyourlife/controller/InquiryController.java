package com.example.projectforyourlife.controller;

import com.example.projectforyourlife.dto.request.InquiryDto;
import com.example.projectforyourlife.service.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestBody InquiryDto inquiryDto){

        inquiryService.sendMail(inquiryDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
