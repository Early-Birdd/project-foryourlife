package com.example.projectforyourlife.service;

import com.example.projectforyourlife.dto.MemberDto;
import com.example.projectforyourlife.dto.MemberResponseDto;
import com.example.projectforyourlife.encoder.BcryptPasswordEncoder;
import com.example.projectforyourlife.entity.Member;
import com.example.projectforyourlife.enumclass.Role;
import com.example.projectforyourlife.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BcryptPasswordEncoder passwordEncoder;

    //throws Exception 공부
    //memberrepository에 저장한 member를 memberresponsedto에 넣어 리턴
    public MemberResponseDto saveMember(MemberDto memberDto) throws Exception{

        if (!validateDuplicatedEmail(memberDto.getEmail())){

            throw new IllegalStateException("이미 가입된 회원입니다.");
        }

        if(!validateDuplicateNickname(memberDto.getNickname())){

            throw new IllegalStateException("중복된 닉네임 입니다.");
        }

        Member newMember = memberDto.toMember();
        Member member = Member.builder()
                .email(newMember.getEmail())
                .password(passwordEncoder.encode(newMember.getPassword()))
                .name(newMember.getName())
                .nickname(newMember.getNickname())
                .role(Role.USER)
                .build();

        return MemberResponseDto.show(memberRepository.save(member));
    }

    public boolean validateDuplicatedEmail(String email){

        if(memberRepository.findByEmail(email) != null){

            return false;
        }

        return true;
    }

    public boolean validateDuplicateNickname(String nickname){

        if(memberRepository.findByNickname(nickname) != null){

            return false;
        }

        return true;
    }
}
