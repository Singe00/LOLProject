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
        private long gameEndTimestamp;
        private String gameMode;
        private int queueId;
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
        private int trueDamageDealtToChampions;
        private int totalDamageTaken;
        private int item0;
        private int item1;
        private int item2;
        private int item3;
        private int item4;
        private int item5;
        private int item6;
        private int goldEarned;
        private int visionScore;
        private int wardsKilled;
        private int totalTimeSpentDead;
        private int totalTimeCCDealt;
        private int timeCCingOthers;
        private int wardsPlaced;
        private int visionWardsBoughtInGame;
        private int detectorWardsPlaced;
        private int doubleKills;
        private int tripleKills;
        private int quadraKills;
        private int pentaKills;
        private int champLevel;
        private int championId;
        private int turretTakedowns;
        private int turretKills;
        private int inhibitorTakedowns;
        private int inhibitorKills;
        private int totalHeal;
        private int totalHealsOnTeammates;
        private int totalDamageShieldedOnTeammates;
        private int objectivesStolen;
        private String summonerId;
        private String championName;
        private String teamPosition;
        private int totalMinionsKilled;
        private int neutralMinionsKilled;
        private boolean win;
        private Perks perks;
        private String puuid;
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

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Perks {
        private StatPerks statPerks;
        private List<Style> styles;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Style {
        private String description;
        private List<Selections> selections;
        private int style;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StatPerks {
        private int defense;
        private int flex;
        private int offense;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Selections {
        private int perk;
    }

}
