package com.example.projectforyourlife.controller;

import com.example.projectforyourlife.dto.request.MemberNameUpdateDto;
import com.example.projectforyourlife.dto.request.MemberNicknameUpdateDto;
import com.example.projectforyourlife.dto.response.MemberResponseDto;
import com.example.projectforyourlife.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getInfo(@PathVariable("id") Long id){

        return new ResponseEntity(mypageService.getMemberInfo(id), HttpStatus.OK);
    }

    @PutMapping("/name")
    public ResponseEntity<Void> updateName(@RequestBody MemberNameUpdateDto memberNameUpdateDto){

        mypageService.updateName(memberNameUpdateDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/nickname")
    public ResponseEntity<Void> updateNickname(@RequestBody MemberNicknameUpdateDto memberNicknameUpdateDto){

        mypageService.updateNickname(memberNicknameUpdateDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
