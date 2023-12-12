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
@Table(name = "likepost")
public class Likepost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 전략을 사용
    @Column(name = "likeid", columnDefinition = "BIGINT", nullable = false)
    private Long likeId;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "postid")
    private Long postId;


}
