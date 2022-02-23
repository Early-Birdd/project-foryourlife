package com.example.projectforyourlife.controller;

import com.example.projectforyourlife.dto.MemberDto;
import com.example.projectforyourlife.dto.MemberResponseDto;
import com.example.projectforyourlife.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody @Valid MemberDto memberDto) throws Exception{

        MemberResponseDto saveMember = memberService.saveMember(memberDto);

        return new ResponseEntity(saveMember.getId(), HttpStatus.OK);
    }

    @GetMapping("/signup/email/{email}")
    public ResponseEntity<Boolean> duplicatedEmail(@PathVariable String email){

        return new ResponseEntity(memberService.validateDuplicatedEmail(email), HttpStatus.OK);
    }

    @GetMapping("/signup/nickname/{nickname}")
    public ResponseEntity<Boolean> duplicatedNickname(@PathVariable String nickname){

        return new ResponseEntity(memberService.validateDuplicateNickname(nickname), HttpStatus.OK);
    }

    @PostMapping("/login")

}
