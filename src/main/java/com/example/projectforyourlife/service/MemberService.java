package com.example.projectforyourlife.service;

import com.example.projectforyourlife.dto.request.*;
import com.example.projectforyourlife.dto.response.MemberResponseDto;
import com.example.projectforyourlife.dto.response.TokenInfoDto;
import com.example.projectforyourlife.entity.Member;
import com.example.projectforyourlife.enumclass.Role;
import com.example.projectforyourlife.jwt.TokenProvider;
import com.example.projectforyourlife.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RedisTemplate redisTemplate;

    //throws Exception 공부
    //memberrepository에 저장한 member를 memberresponsedto에 넣어 리턴
    @Transactional
    public MemberResponseDto saveMember(MemberDto memberDto) throws Exception{

        if (!validateDuplicatedEmail(memberDto.getEmail())){

            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }

        if(!validateDuplicateNickname(memberDto.getNickname())){

            throw new IllegalArgumentException("중복된 닉네임 입니다.");
        }

        Member newMember = memberDto.toMember();
        Member member = Member.builder()
                .email(newMember.getEmail())
                .password(passwordEncoder.encode(newMember.getPassword()))
                .name(newMember.getName())
                .nickname(newMember.getNickname())
                .role(Role.ROLE_USER)
                .build();

        //memberRepository에 있는 member를 매개변수로 MemberResponseDto 정보를 show
        return MemberResponseDto.show(memberRepository.save(member));
    }

    public boolean validateDuplicatedEmail(String email){

        if(memberRepository.findByEmail(email) == null){

            return false;
        }

        return true;
    }

    public boolean validateDuplicateNickname(String nickname){

        if(memberRepository.findByNickname(nickname) == null){

            return false;
        }

        return true;
    }

    @Transactional
    public TokenInfoDto login(LoginDto loginDto){

        if(memberRepository.findByEmail(loginDto.getEmail()) == null){

            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        //Login id, pw 로 AuthenticationToken 생성
        //인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication();

        //비밀번호 체크
        //authenticate 메서드 실행 시 CustomUserDetailService 의 loadUserByUsername 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //인증 정보 기반의  JWT 생성
        TokenInfoDto tokenInfoDto = tokenProvider.generateTokenDto(authentication);

        //RefreshToken Redis 저장, expirationTime 설정으로 자동 삭제
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfoDto.getRefreshToken()
                        , tokenInfoDto.getAccessTokenExpiresIn(), TimeUnit.MILLISECONDS);

        return tokenInfoDto;
    }

    @Transactional
    public TokenInfoDto reissue(TokenRequestDto tokenRequestDto){

        //Refresh Token 검증
        if(!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())){

            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }

        //Access Token 에서 User email 을 가져온다.
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        //Redis 에서 User email을 기반으로 저장된 Refresh Token 값을 가져온다.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());

        //로그아웃되어 Redis에 Refresh Token이 없는 경우
        if(ObjectUtils.isEmpty(refreshToken)){

            throw new RuntimeException("잘못된 요청입니다.");
        }

        if(!refreshToken.equals(tokenRequestDto.getRefreshToken())){

            throw new RuntimeException("Refresh Token 정보가 일치하지 않습니다.");
        }

        TokenInfoDto tokenInfoDto = tokenProvider.generateTokenDto(authentication);

        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfoDto.getRefreshToken()
                        , tokenInfoDto.getAccessTokenExpiresIn(), TimeUnit.MILLISECONDS);

        return tokenInfoDto;
    }

    public String logout(LogoutDto logoutDto){

        //Access Token 검증
        if(!tokenProvider.validateToken(logoutDto.getAccessToken())){

            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        //Access Token 에서 User email을 가져온다.
        Authentication authentication = tokenProvider.getAuthentication(logoutDto.getAccessToken());

        //Redis에서 해당 User email로 저장된 Refresh Token 여부 확인 후 있다면 삭제
        if(redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null){

            //Refresh Token 삭제
            redisTemplate.delete("RT:" + authentication.getName());
        }

        //해당 Access Token 의 유효시간을 BlackList로 저장
        Long expiration = tokenProvider.getExpiration(logoutDto.getAccessToken());
        redisTemplate.opsForValue().set(logoutDto.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

        return "로그아웃 되었습니다.";
    }


}
