package com.example.lwp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MasteryDto {
    private long championId;
    private int championLevel;
    private int championPoints;
}
