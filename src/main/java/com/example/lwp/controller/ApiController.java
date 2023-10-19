package com.example.lwp.controller;

import com.example.lwp.domain.Champion;
import com.example.lwp.domain.Ranking;
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
        List<MatchDto> m = apiService.FindMatch(request.getSummonerName(),request.getIndex());
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

    @PostMapping("/ranking")
    @ResponseBody
    public List<Object[]> ranking(@RequestBody RankingDto request) {
        List<Object[]> ranking = apiService.ReturnRankingList(request.getGametype());

        return ranking;
    }

    @PostMapping("/timeline")
    @ResponseBody
    public MatchTimelineDto search(@RequestBody TimelineDto request) {
        MatchTimelineDto m = apiService.FindMatchTimeline(request.getMatchId());
        return m;

    }

    @PostMapping("/champion")
    @ResponseBody
    public List<Champion> champion() {
        List<Champion> championList = apiService.ReturnChampionList();
        return championList;
    }

    @PostMapping("/champion2")
    @ResponseBody
    public List<Champion> champion2(@RequestBody SearchChampionDto request) {
        List<Champion> championList = apiService.SearchChampionWithOptions(request.getInputValue(),request.getSelectedRole());

        return championList;
    }

    @PostMapping("/champion3")
    @ResponseBody
    public List<Champion> champion3() {
        List<Champion> championList = apiService.ReturnChampions();

        return championList;
    }
}
