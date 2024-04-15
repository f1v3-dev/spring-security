package com.f1v3.security.service;

import com.f1v3.security.doamin.Member;
import com.f1v3.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterMemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public Long join(String userId, String password) {

        // 동일한 ID를 사용하는 회원이 있는지 확인
        validateDuplicateMember(userId);

        // PasswordEncoder -> BCrypt 방식으로 암호화하여 저장
        Member member = new Member(userId, passwordEncoder.encode(password));
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateMember(String userId) {
        memberRepository.findByUserid(userId)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
