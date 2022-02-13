package com.hanghae.mini2.riceFriend.controller;

import com.hanghae.mini2.riceFriend.dto.request.LoginRequestDto;
import com.hanghae.mini2.riceFriend.dto.request.SignupRequestDto;
import com.hanghae.mini2.riceFriend.dto.response.CMResponseDto;
import com.hanghae.mini2.riceFriend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<CMResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/user/login")
    public ResponseEntity<CMResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto,
                                               HttpServletResponse response) {
        return userService.login(requestDto, response);
    }

    @PostMapping("/user/idCheck")
    public ResponseEntity<CMResponseDto> idCheck(@RequestBody SignupRequestDto requestDto) {
        return userService.idCheck(requestDto);
    }
}
