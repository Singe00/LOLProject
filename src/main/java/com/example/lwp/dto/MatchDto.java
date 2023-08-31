package com.example.lwp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MatchDto {
    private Metadata metadata;
    private Info info;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Metadata {
        private String matchId;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Info {
        private long gameCreation;
        private int gameDuration;
        private String gameMode;
        private List<Participant> participants;
        private List<Teams> teams;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Participant {
        private String summonerName;
        private int profileIcon;
        private int summonerLevel;
        private int teamId;
        private int assists;
        private int deaths;
        private int kills;
        private int summoner1Id;
        private int summoner2Id;
        private int totalDamageDealtToChampions;
        private int totalDamageShieldedOnTeammates;
        private int item0;
        private int item1;
        private int item2;
        private int item3;
        private int item4;
        private int item5;
        private int item6;
        private int goldEarned;
        private int visionScore;
        private int wardsPlaced;
        // 필요한 필드만 선언
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Teams {
        private int teamId;
        private boolean win;
        private Objectives objectives;
        // ... 나머지 필요한 Team 필드 추가
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Objectives {
        private ObjectiveType baron;
        private ObjectiveType champion;
        private ObjectiveType dragon;
        private ObjectiveType inhibitor;
        private ObjectiveType riftHerald;
        private ObjectiveType tower;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ObjectiveType {
        private int kills;
    }
}
