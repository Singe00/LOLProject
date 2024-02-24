package com.example.lwp.service;

import com.example.lwp.config.RiotConstant;
import com.example.lwp.domain.Champion;
import com.example.lwp.dto.MasteryDto;
import com.example.lwp.dto.RotationDto;
import com.example.lwp.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Component
public class Update {

    private final ChampionRepository championRepository;

    @Autowired
    public Update(ChampionRepository championRepository){
        this.championRepository = championRepository;
    }

    // 매주 화요일 5시에 실행
    @Scheduled(cron = "0 0 4 ? * 2")
    public void updateChampionRotation() {
        // 실행할 작업 내용을 여기에 작성
        System.out.println("챔피언 로테이션 업데이트 : " + new Date());

        //matchid 가져오기
        String apiUrl = "https://kr.api.riotgames.com/lol/platform/v3/champion-rotations?api_key="+ RiotConstant.API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RotationDto> rotationResponse = restTemplate.getForEntity(apiUrl, RotationDto.class);

        if (rotationResponse.getStatusCode() == HttpStatus.OK) {
            RotationDto rotationDto = rotationResponse.getBody();
            if (rotationDto != null) {

                List<Integer> freeChampionIds = rotationDto.getFreeChampionIds();

                System.out.println(freeChampionIds);
                // Fetch all champions from the database
                List<Champion> champions = championRepository.findAll();

                for (Champion champion : champions) {
                    int championId = champion.getChampionId();
                    champion.setRotation(freeChampionIds.contains(championId) ? 1 : 0);
                }

                // Save the updated champions with rotation info
                championRepository.saveAll(champions);
            }
        }

    }

/*
    @Scheduled(cron = "0 0 5 ? * TUE")
    public void updateRanking() {
        // 실행할 작업 내용을 여기에 작성
        System.out.println("소환사 랭킹 업데이트 : " + new Date());


        //챌린져
        String apiUrl = "https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5?api_key="+ RiotConstant.API_KEY;
        String apiUrl2 = "https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/RANKED_FLEX_SR?api_key="+ RiotConstant.API_KEY;
        //그마
        String apiUrl3 = "https://kr.api.riotgames.com/lol/league/v4/grandmasterleagues/by-queue/RANKED_SOLO_5x5?api_key="+ RiotConstant.API_KEY;
        String apiUrl4 = "https://kr.api.riotgames.com/lol/league/v4/grandmasterleagues/by-queue/RANKED_FLEX_SR?api_key="+ RiotConstant.API_KEY;
        //마스터
        String apiUrl5 = "https://kr.api.riotgames.com/lol/league/v4/masterleagues/by-queue/RANKED_SOLO_5x5?api_key="+ RiotConstant.API_KEY;
        String apiUrl6 = "https://kr.api.riotgames.com/lol/league/v4/masterleagues/by-queue/RANKED_FLEX_SR?api_key="+ RiotConstant.API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RotationDto> rotationResponse = restTemplate.getForEntity(apiUrl, RotationDto.class);

        if (rotationResponse.getStatusCode() == HttpStatus.OK) {
            RotationDto rotationDto = rotationResponse.getBody();
            if (rotationDto != null) {

                List<Integer> freeChampionIds = rotationDto.getFreeChampionIds();

                System.out.println(freeChampionIds);
                // Fetch all champions from the database
                List<Champion> champions = championRepository.findAll();

                for (Champion champion : champions) {
                    int championId = champion.getChampionId();
                    champion.setRotation(freeChampionIds.contains(championId) ? 1 : 0);
                }

                // Save the updated champions with rotation info
                championRepository.saveAll(champions);
            }
        }

    }*/
}
