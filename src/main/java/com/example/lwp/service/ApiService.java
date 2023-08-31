package com.example.lwp.service;

import com.example.lwp.config.RiotConstant;
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

    public MatchDto FindMatchWithSummonerName(String sn){

        //puuid 가져오기
        String apiUrl1 = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+sn+"?api_key="+RiotConstant.API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SummonerDto> response1= restTemplate.getForEntity(apiUrl1, SummonerDto.class);
        String puuid = response1.getBody().getPuuid();

        //matchid 가져오기
        String apiUrl2 = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/"+puuid+"/ids?start=0&count=100&api_key="+RiotConstant.API_KEY;
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

        MatchDto matchDto = new MatchDto();
            try {
                String apiUrl3 = "https://asia.api.riotgames.com/lol/match/v5/matches/"+matchIdsList.get(0)+"?api_key="+RiotConstant.API_KEY;
                ObjectMapper objectMapper = new ObjectMapper();
                matchDto = objectMapper.readValue(new URL(apiUrl3), MatchDto.class);

                System.out.println("Match ID: " + matchDto.getMetadata().getMatchId());
                System.out.println("Game Creation: " + matchDto.getInfo().getGameCreation());
                System.out.println("Game Duration: " + matchDto.getInfo().getGameDuration());

                System.out.println(matchDto.getInfo().getParticipants().get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }

/*        if response.json()["gameMode"] =="CLASSIC":
        gamemode = "소환사의 협곡"
        elif response.json()["gameMode"] =="ARAM":
        gamemode = "칼바람 나락"
        elif response.json()["gameMode"] =="CHERRY":
        gamemode = "아레나"
        elif response.json()["gameMode"] =="URF":
        gamemode = "우르프"
        elif response.json()["gameMode"] =="SIEGE":
        gamemode = "돌격 넥서스"
        elif response.json()["gameMode"] == "ONEFORALL":
        gamemode = "단일챔피언"
        elif response.json()["gameMode"] == "ARSR":
        gamemode = "All Random Summoner's Rift games"
            else:
        gamemode = response.json()["gameMode"]*/

/*        if response.json()["gameType"] == "MATCHED_GAME":
        if response.json()["gameQueueConfigId"] == 420:
        gametype = "솔로 랭크"
        elif response.json()["gameQueueConfigId"] == 430:
        gametype = "일반 게임"
        elif response.json()["gameQueueConfigId"] == 100:
        gametype = "칼바람 나락"
        elif response.json()["gameQueueConfigId"] in(1090, 1100, 1110):
        gametype = "롤토체스"
                else:
        gametype = "자유 랭크"*/

        return matchDto;
    }
}