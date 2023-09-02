package com.example.lwp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SummonerRankInfoDto {
    private String queueType = "";
    private String tier ="Unranked";
    private String rank ="";
    private int wins = 0;
    private int losses = 0;
}

