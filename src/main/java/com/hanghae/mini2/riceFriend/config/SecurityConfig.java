package com.hanghae.mini2.riceFriend.config;

import com.hanghae.mini2.riceFriend.config.jwt.JwtAuthenticationFilter;
import com.hanghae.mini2.riceFriend.config.jwt.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안함.
                .and()
                .addFilter(corsFilter) // 모든 요청은 이 필터를 탄다. cors 정책에서 벗어날 수 있다.
                .addFilterBefore(new JwtAuthenticationFilter(jwtAuthenticationProvider), UsernamePasswordAuthenticationFilter.class)
                .formLogin().disable() // 기본 로그인 방식 안쓸거임.
                .httpBasic().disable() // 기본 인증 방식을 안쓸거임.

                .authorizeRequests()
                .anyRequest()
                .permitAll();

    }
}