package com.example.lwp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 전략을 사용
    @Column(name = "postid", columnDefinition = "BIGINT", nullable = false)
    private Long postId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "category")
    private Long category;

    @Column(name = "look")
    private Long look;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "like_count")
    private Long like_count;

    @PrePersist
    private void prePersist() {
        this.look = 0L;
        this.like_count = 0L;
    }

}
