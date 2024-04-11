package com.f1v3.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginDto {

    private String userId;

    private String password;
}
