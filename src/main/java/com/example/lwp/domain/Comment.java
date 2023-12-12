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
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 전략을 사용
    @Column(name = "commentid", columnDefinition = "BIGINT", nullable = false)
    private Long commentId;

    @Column(name = "text")
    private String text;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "postid")
    private Long postId;

}
