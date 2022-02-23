package com.example.projectforyourlife.config;

import com.example.projectforyourlife.encoder.BcryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //http 요청에 대한 보안 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().disable()
                .csrf().disable();
    }

    @Bean
    public BcryptPasswordEncoder passwordEncoder(){

        return new BcryptPasswordEncoder();
    }
}
