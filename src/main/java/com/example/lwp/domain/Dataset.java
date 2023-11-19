package com.example.lwp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Table(name = "dataset")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Dataset {

    @Id
    @Column(name = "championid")
    private int championId;
    @Column(name = "championname")
    private String championName;
    @Column(name = "teamposition")
    private String teamPosition;
    @Column(name = "win")
    private int win;
    @Column(name = "lose")
    private int lose ;
    @Column(name = "times")
    private String timeS;
    @Column(name = "spell")
    private String spell;
    @Column(name = "skill")
    private String skill;
    @Column(name = "skevole")
    private String skevole;
    @Column(name = "startitems")
    private String startItems;
    @Column(name = "core1")
    private String core1;
    @Column(name = "core2")
    private String core2;
    @Column(name = "core3")
    private String core3;
    @Column(name = "core4")
    private String core4;
    @Column(name = "fragment")
    private String fragment;
    @Column(name = "mr")
    private String mr;
    @Column(name = "sr")
    private String sr;

}
