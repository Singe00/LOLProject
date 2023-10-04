package com.example.lwp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MasteryDto {
    private long championId;
    private long lastPlayTime;
    private int championLevel;
    private int championPoints;
    private String championName;
}
