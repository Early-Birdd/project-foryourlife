package com.example.projectforyourlife.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//OncePerRequestFilter -> 인터페이스를 구현하므로 요청 받을 시 단 한번 실행
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer";

    private final TokenProvider tokenProvider;
    private final RedisTemplate redisTemplate;

    //JWT 인증 정보를 현재 스레드 SecurityContext에 저장
    //토큰 정보가 없거사 유효하지 않으면 정상 수행 X
    //요청이 정상적으로 Controller 까지 도달하면 SecurityContext에 Member Id 존재가 보장
    //직접 DB를 조회한 것이 아닌 Access Token에 있는 Member Id를 꺼낸 것이므로 탈퇴로 인한 경우 등은 Service단에서 고려
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Request Header에서 토큰을 꺼낸다.
        String jwt = resolveToken(request);

        //토큰의 유효성 검사
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {

            //redis에 accesstoken logout 확인
            String isLogout = (String) redisTemplate.opsForValue().get(jwt);

            if (ObjectUtils.isEmpty(isLogout)){

                Authentication authentication = tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    //Request Header에서 토큰 정보를 꺼낸다.
    private String resolveToken(HttpServletRequest request){

        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)){

            return bearerToken.substring(7);
        }

        return null;
    }
}
