package com.example.projectforyourlife.service;

import com.example.projectforyourlife.dto.request.MemberNameUpdateDto;
import com.example.projectforyourlife.dto.request.MemberNicknameUpdateDto;
import com.example.projectforyourlife.dto.response.MemberResponseDto;
import com.example.projectforyourlife.entity.Member;
import com.example.projectforyourlife.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MypageService {

    private final MemberRepository memberRepository;

    public MemberResponseDto getMemberInfo(Long id){

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return MemberResponseDto.show(member);
    }

    public void updateName(MemberNameUpdateDto memberNameUpdateDto){

        Member member = memberRepository.findById(memberNameUpdateDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        member.updateName(memberNameUpdateDto.getName());
    }

    public void updateNickname(MemberNicknameUpdateDto memberNicknameUpdateDto){

        Member member = memberRepository.findById(memberNicknameUpdateDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        member.updateNickname(memberNicknameUpdateDto.getNickname());
    }
}
