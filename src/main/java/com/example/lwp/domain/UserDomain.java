package com.example.lwp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 전략을 사용
    @Column(name = "userid", columnDefinition = "BIGINT", nullable = false)
    private Long userId;

    @Column(name = "email", columnDefinition = "VARCHAR(100)",nullable = false)
    private String email;

    @Column(name = "oauth_type", columnDefinition = "VARCHAR(50)")
    private String oauthType;
}
