package com.example.lwp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MatchTimelineDto {
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
        private long frameInterval;
        private List<Frame> frames;
        private List<Participant> participants;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Frame {
        private List<Event> events;
        private long timestamp;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Event {
        private String type;
        private int itemId;
        private String levelUpType;
        private int skillSlot;
        private int participantId;
        private long timestamp;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Participant {
        private int participantId;
        private String puuid;
    }


}
