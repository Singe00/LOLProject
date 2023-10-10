package com.example.lwp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Table(name = "champion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Champion {

    @Id
    @Column(name = "championid")
    private int championId;
    @Column(name = "championname")
    private String championName;
    @Column(name = "championnamekr")
    private String championNameKr;
    @Column(name = "nickname")
    private String nickName;
    @Column(name = "rotation")
    private int rotation;
    @Column(name = "position")
    private String position;
    @Column(name = "initial")
    private String initial;
}
