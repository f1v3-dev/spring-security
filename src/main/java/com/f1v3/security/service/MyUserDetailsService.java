package com.f1v3.security.service;

import com.f1v3.security.doamin.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberEntity member = memberService.findMember(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자가 없어요.. ㅠㅠ"));


        return User.builder()
                .username(member.getUserId())
                .password(member.getPassword())
                .roles(member.getRoles())
                .build();
    }
}
