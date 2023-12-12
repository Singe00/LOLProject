package com.example.lwp.controller;

import com.example.lwp.domain.Champion;
import com.example.lwp.domain.Dataset;
import com.example.lwp.dto.*;
import com.example.lwp.service.ApiService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/dataset")
    @ResponseBody
    public List<Dataset> dataset(@RequestBody DatasetDto request) {
        List<Dataset> datasetList = apiService.ReturnDataset(request.getChampionName());

        return datasetList;
    }

    @PostMapping("/dataset2")
    @ResponseBody
    public Dataset dataset2(@RequestBody DatasetDto request) {
        Dataset dataSet = apiService.ReturnDataset2(request.getChampionName(),request.getLane());

        if (dataSet == null){
            return null;
        }
        return dataSet;
    }

    @GetMapping("/healthCheck")
    @ResponseStatus(HttpStatus.OK)
    public boolean healthCheck() {
        return true;
    }

    @GetMapping("/check-session")
    public boolean checkSession(@RequestHeader(name = "LPGGCOOKIE") String lpggCookie, HttpSession session, HttpServletResponse response) {
        // 세션에서 저장된 email 값을 가져옴
        String storedEmail = (String) session.getAttribute("email");

        if (storedEmail==null){
            Cookie cookie = new Cookie("LPGGCOOKIE", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);

            return false;
        }


        if (lpggCookie.equals(storedEmail)){
            return true;
        }
        else {
            return false;
        }
    }


}
