package com.example.lwp.controller;

import com.example.lwp.dto.*;
import com.example.lwp.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private final ApiService apiService;

    @PostMapping("/user")
    @ResponseBody
    public SummonerInfoDto user(@RequestBody SearchDto request) {
        SummonerInfoDto sid = apiService.FindSummonerInfo(request.getSummonerName());
        if (sid == null){
            return null;
        }
        return sid;
    }

    @PostMapping("/match")
    @ResponseBody
    public List<MatchDto> search(@RequestBody SearchDto request) {
        List<MatchDto> m = apiService.FindMatch(request.getSummonerName());
        if (m == null){
            return null;
        }
        return m;
    }

    @PostMapping("/mastery")
    @ResponseBody
    public List<MasteryDto> mastery(@RequestBody SearchDto request) {
        List<MasteryDto> m = apiService.FindMastery(request.getSummonerName());

        if (m == null){
            return null;
        }
        return m;
    }

    @PostMapping("/rating")
    @ResponseBody
    public List<MasteryDto> rating(@RequestBody SearchDto request) {
        List<MasteryDto> m = apiService.FindMastery(request.getSummonerName());

        if (m == null){
            return null;
        }
        return m;
    }

    @PostMapping("/timeline")
    @ResponseBody
    public MatchTimelineDto search(@RequestBody TimelineDto request) {
        MatchTimelineDto m = apiService.FindMatchTimeline(request.getMatchId());
        return m;

    }

}
