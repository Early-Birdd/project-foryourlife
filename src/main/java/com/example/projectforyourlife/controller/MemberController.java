package com.example.projectforyourlife.controller;

import com.example.projectforyourlife.dto.request.LoginDto;
import com.example.projectforyourlife.dto.request.LogoutDto;
import com.example.projectforyourlife.dto.request.MemberDto;
import com.example.projectforyourlife.dto.request.TokenRequestDto;
import com.example.projectforyourlife.dto.response.TokenInfoDto;
import com.example.projectforyourlife.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody @Valid MemberDto memberDto) throws Exception{

        return new ResponseEntity(memberService.saveMember(memberDto), HttpStatus.OK);
    }

    @GetMapping("/signup/emails/{email}")
    public ResponseEntity<Boolean> duplicatedEmail(@PathVariable String email){

        return new ResponseEntity(memberService.validateDuplicatedEmail(email), HttpStatus.OK);
    }

    @GetMapping("/signup/nicknames/{nickname}")
    public ResponseEntity<Boolean> duplicatedNickname(@PathVariable String nickname){

        return new ResponseEntity(memberService.validateDuplicateNickname(nickname), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenInfoDto> login(@RequestBody LoginDto loginDto){

        return new ResponseEntity(memberService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenInfoDto> reissue(@RequestBody TokenRequestDto tokenRequestDto){

        return new ResponseEntity(memberService.reissue(tokenRequestDto), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutDto logoutDto){

        return new ResponseEntity(memberService.logout(logoutDto), HttpStatus.OK);
    }
}
