package com.f1v3.security.service;

import com.f1v3.security.doamin.Member;
import com.f1v3.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findMember(String userId) {
        return memberRepository.findByUserId(userId);
    }

    public boolean isValidMember(String userId, String password) {

        return findMember(userId)
                .filter(member -> member.getPassword().equals(password))
                .isPresent();
    }
}
