package com.f1v3.security.doamin;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NonNull
    private String userId;

    @NonNull
    private String password;

    @NonNull
    private String roles;


    public Member(@NonNull String userId, @NonNull String password) {
        this.userId = userId;
        this.password = password;
        this.roles = "USER";
    }
}
