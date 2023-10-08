package com.example.lwp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
    private int championId;
    private String championName;
    private int wins;
    private int lose;
    private int cs;
    private int pcs;
    private int kills;
    private int deaths;
    private int assists;
    private String teamPosition;
}
