package com.f1v3.security.service;

import com.f1v3.security.doamin.MemberEntity;
import com.f1v3.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<MemberEntity> findMember(String userId) {
        return memberRepository.findByUserId(userId);
    }

    public boolean isValidMember(String userId, String password) {

        // 입력받은 비밀번호를 저장된 비밀번호와 대조하기 위해 암호화 후 비교
        String encodedPassword = passwordEncoder.encode(password);

        return findMember(userId)
                .filter(member -> member.getPassword().equals(encodedPassword))
                .isPresent();
    }
}
