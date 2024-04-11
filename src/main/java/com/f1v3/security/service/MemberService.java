package com.f1v3.security.service;

import com.f1v3.security.doamin.Member;
import com.f1v3.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMember(String userId) {
        return memberRepository.findByUserId(userId)
                .orElseThrow(RuntimeException::new);
    }

    public boolean isValidMember(String userId, String password) {
        Member member = findMember(userId);

        return member.getPassword().equals(password);
    }
}
