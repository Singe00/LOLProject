package com.example.lwp.service;

import com.example.lwp.config.RiotConstant;
import com.example.lwp.dto.MatchDto;
import com.example.lwp.dto.SummonerDto;
import com.example.lwp.dto.SummonerInfoDto;
import com.example.lwp.dto.SummonerRankInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiService {


    public SummonerInfoDto FindSummonerInfo(String sn){

        SummonerInfoDto summonerInfoDto = new SummonerInfoDto();

        List<SummonerRankInfoDto> summonerRankInfoDtos = new ArrayList<>();
        // 첫 번째 빈 객체 초기화 및 추가
        SummonerRankInfoDto dto1 = new SummonerRankInfoDto();
        summonerRankInfoDtos.add(dto1);

        // 두 번째 빈 객체 초기화 및 추가
        SummonerRankInfoDto dto2 = new SummonerRankInfoDto();
        summonerRankInfoDtos.add(dto2);

        //User Info
        String apiUrl1 = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+sn+"?api_key="+RiotConstant.API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SummonerDto> response1= restTemplate.getForEntity(apiUrl1, SummonerDto.class);



        //User Rank Info
        String apiUrl2 = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+response1.getBody().getId()+"?api_key="+RiotConstant.API_KEY;
        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<SummonerRankInfoDto[]> response2 = restTemplate2.getForEntity(apiUrl2, SummonerRankInfoDto[].class);

        SummonerRankInfoDto[] summonerRankInfoDto = response2.getBody();


        if (summonerRankInfoDto != null && summonerRankInfoDto.length > 0) {
            for (SummonerRankInfoDto s :summonerRankInfoDto){
                if ("RANKED_SOLO_5x5".equals(s.getQueueType())){
                    summonerRankInfoDtos.get(0).setRank(s.getRank());
                    summonerRankInfoDtos.get(0).setTier(s.getTier());
                    summonerRankInfoDtos.get(0).setWins(s.getWins());
                    summonerRankInfoDtos.get(0).setLosses(s.getLosses());
                } else if ("RANKED_FLEX_SR".equals(s.getQueueType())) {
                    summonerRankInfoDtos.get(1).setRank(s.getRank());
                    summonerRankInfoDtos.get(1).setTier(s.getTier());
                    summonerRankInfoDtos.get(1).setWins(s.getWins());
                    summonerRankInfoDtos.get(1).setLosses(s.getLosses());
                }
            }
        }

        summonerInfoDto.setSummonerName(response1.getBody().getName());
        summonerInfoDto.setProfileIcon(response1.getBody().getProfileIconId());
        summonerInfoDto.setSummonerLevel(response1.getBody().getSummonerLevel());
        summonerInfoDto.setRanks(summonerRankInfoDtos);

        System.out.println(summonerInfoDto.getRanks());
        return summonerInfoDto;
    }

    public List<MatchDto> FindMatch(String sn){

        //puuid 가져오기
        String apiUrl1 = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+sn+"?api_key="+RiotConstant.API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SummonerDto> response1= restTemplate.getForEntity(apiUrl1, SummonerDto.class);
        String puuid = response1.getBody().getPuuid();

        //matchid 가져오기
        String apiUrl2 = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/"+puuid+"/ids?start=0&count=10&api_key="+RiotConstant.API_KEY;
        restTemplate = new RestTemplate();
        ResponseEntity<String[]> response2= restTemplate.getForEntity(apiUrl2, String[].class);
        String[] matchIds = response2.getBody();

        List<String> matchIdsList = Arrays.asList(matchIds);
        List<MatchDto> matchDtoList = new ArrayList<>();

        for(String mi : matchIdsList){
            MatchDto matchDto = new MatchDto();

            try {
                String apiUrl3 = "https://asia.api.riotgames.com/lol/match/v5/matches/"+mi+"?api_key="+RiotConstant.API_KEY;
                ObjectMapper objectMapper = new ObjectMapper();
                matchDto = objectMapper.readValue(new URL(apiUrl3), MatchDto.class);

                /*
                * 1 SummonerCleanse (정화)
                * 3 SummonerExhaust (탈진)
                * 4 SummonerFlash (점멸)
                * 6 SummonerGhost (유령)
                * 7 SummonerHeal (회복)
                * 11 SummonerSmite (강타)
                * 12 SummonerTeleport (순간이동)
                * 13 SummonerClarity (총명)
                * 14 SummonerIgnite (점화)
                * 21 SummonerBarrier (방어막)
                * 32 SummonerMark (표식)
                * 2201 SummonerCherryHold
                * 2202 SummonerCherryFlash
                * */

                if (matchDto.getInfo().getGameMode().equals("CLASSIC")){
                    if (matchDto.getInfo().getQueueId()==420){
                        matchDto.getInfo().setGameMode("솔로 랭크");
                    }else if (matchDto.getInfo().getQueueId()==430){
                        matchDto.getInfo().setGameMode("일반 게임");
                    }else if (matchDto.getInfo().getQueueId()==440){
                        matchDto.getInfo().setGameMode("자유 랭크");
                    }
                } else if (matchDto.getInfo().getGameMode().equals("ARAM")) {
                    matchDto.getInfo().setGameMode("칼바람 나락");
                } else if (matchDto.getInfo().getGameMode().equals("CHERRY")) {
                    matchDto.getInfo().setGameMode("아레나");
                } else {
                    matchDto.getInfo().setGameMode("특별 모드");
                }
                matchDtoList.add(matchDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(matchDtoList.get(0));
        System.out.println(matchDtoList.get(1));


        return matchDtoList;
    }
}

