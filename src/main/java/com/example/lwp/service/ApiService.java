package com.example.lwp.service;

import com.example.lwp.dto.MatchDto;
import com.example.lwp.dto.SummonerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiService {

    public List<String> FindMatchWithSummonerName(String sn){
        //apikey
        String apiKey = "RGAPI-ee6d6424-4128-424f-8455-6cad20e19ae6"; // 본인의 API 키로 변경해야 합니다.

        //puuid 가져오기
        String apiUrl1 = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+sn+"?api_key="+apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SummonerDto> response1= restTemplate.getForEntity(apiUrl1, SummonerDto.class);
        String puuid = response1.getBody().getPuuid();

        //matchid 가져오기
        String apiUrl2 = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/"+puuid+"/ids?start=0&count=100&api_key="+apiKey;
        restTemplate = new RestTemplate();
        ResponseEntity<String[]> response2= restTemplate.getForEntity(apiUrl2, String[].class);
        String[] matchIds = response2.getBody();
        List<String> matchIdsList = Arrays.asList(matchIds);

/*
            String apiUrl3 = "https://asia.api.riotgames.com/lol/match/v5/matches/"+row+"?api_key="+apiKey;
            restTemplate = new RestTemplate();
            ResponseEntity<SummonerDto> response3= restTemplate.getForEntity(apiUrl3, SummonerDto.class);
            String puuid = response1.getBody().getPuuid();
*/

            try {
                String apiUrl3 = "https://asia.api.riotgames.com/lol/match/v5/matches/"+matchIdsList.get(0)+"?api_key="+apiKey;
                ObjectMapper objectMapper = new ObjectMapper();
                MatchDto matchDto = objectMapper.readValue(new URL(apiUrl3), MatchDto.class);

                System.out.println("Match ID: " + matchDto.getMetadata().getMatchId());
                System.out.println("Game Creation: " + matchDto.getInfo().getGameCreation());
                System.out.println("Game Duration: " + matchDto.getInfo().getGameDuration());

                System.out.println(matchDto.getInfo());
                // 여기에 다른 필요한 정보를 출력하는 로직 추가

            } catch (IOException e) {
                e.printStackTrace();
            }




        return matchIdsList;
    }
}
