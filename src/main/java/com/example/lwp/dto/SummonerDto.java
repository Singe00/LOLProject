package com.example.lwp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SummonerDto {
    public String id;
    public String accountId;
    public String puuid;
    public String name;
    public int profileIconId;
    public long revisionDate;
    public long summonerLevel;
}
