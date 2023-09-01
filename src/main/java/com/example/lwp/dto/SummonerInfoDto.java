package com.example.lwp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SummonerInfoDto {
    private String id;
    private String summonerName;
    private int profileIcon;
    private long summonerLevel;
    private List<SummonerRankInfoDto> ranks = new ArrayList<>(); // SummonerRankInfoDto 리스트 추가

}
