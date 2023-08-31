package com.example.lwp.controller;

import com.example.lwp.dto.MatchDto;
import com.example.lwp.dto.SearchDto;
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
public class ApiController {

    @Autowired
    private final ApiService apiService;

    @PostMapping("/search")
    @ResponseBody
    public ResponseEntity<MatchDto> search(@RequestBody SearchDto request, Model model) {
        MatchDto m = apiService.FindMatchWithSummonerName(request.getSummonerName());
        model.addAttribute("jsonData",m);
        return ResponseEntity.ok(m);

    }
}
