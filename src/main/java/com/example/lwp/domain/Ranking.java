package com.example.lwp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "ranking")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Ranking {

    @Id
    @Column(name = "summonername")
    private String summonerName;

    @Column(name = "wins")
    private int wins;

    @Column(name = "losses")
    private int losses;

    @Column(name = "tier")
    private String tier;

    @Column(name = "leaguepoints")
    private int leaguePoints;

    @Column(name = "profileiconid")
    private int profileIconId;

    @Column(name = "championid1")
    private int championId1;

    @Column(name = "championid2")
    private int championId2;

    @Column(name = "championid3")
    private int championId3;

    @Column(name = "gametype")
    private int gametype;
}
