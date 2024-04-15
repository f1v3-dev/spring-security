package com.f1v3.security.controller;

import com.f1v3.security.dto.MemberLoginDto;
import com.f1v3.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/login-process")
    public String login(MemberLoginDto dto) {
        boolean isValid = memberService.isValidMember(dto.getUserId(), dto.getPassword());

        if (isValid) {
            return "dashboard";
        }

        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "login";
    }
}
