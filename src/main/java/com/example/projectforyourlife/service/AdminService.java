package com.example.projectforyourlife.service;

import com.example.projectforyourlife.dto.response.MemberListResponseDto;
import com.example.projectforyourlife.entity.Member;
import com.example.projectforyourlife.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final MemberRepository memberRepository;

    public Page<MemberListResponseDto> getAllMember(Pageable pageable){

        Page<Member> members = memberRepository.findAll(pageable);

        Page<MemberListResponseDto> memberListResponseDtos = members.map(
                member -> new MemberListResponseDto(member.getId(), member.getEmail(),
                        member.getName(), member.getNickname()));

        return memberListResponseDtos;
    }
}
