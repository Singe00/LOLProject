package com.example.lwp.controller;

import com.example.lwp.dto.SearchDto;
import com.example.lwp.dto.SummonerDto;
import com.example.lwp.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class ApiController {

    @Autowired
    private final ApiService apiService;

    @PostMapping("/search")
    @ResponseBody
    public String search(@RequestBody SearchDto request) {
        apiService.FindMatchWithSummonerName(request.getSummonerName());
        return request.getSummonerName();



    }
}
