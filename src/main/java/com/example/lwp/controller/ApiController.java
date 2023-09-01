package com.example.lwp.controller;

import com.example.lwp.dto.MatchDto;
import com.example.lwp.dto.SearchDto;
import com.example.lwp.dto.SummonerInfoDto;
import com.example.lwp.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(sid);
        return sid;
    }

    @PostMapping("/search")
    @ResponseBody
    public String search(@RequestParam SearchDto request) {
        MatchDto m = apiService.FindMatchWithSummonerName(request.getSummonerName());
        return "ok";

    }


}
