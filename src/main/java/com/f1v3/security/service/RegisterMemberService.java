package com.f1v3.security.service;

import com.f1v3.security.doamin.Member;
import com.f1v3.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterMemberService {

    private final MemberRepository memberRepository;

    public Long join(String userId, String password) {

        validateDuplicateMember(userId);

        Member member = new Member(userId, password);
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateMember(String userId) {
        memberRepository.findByUserId(userId)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
