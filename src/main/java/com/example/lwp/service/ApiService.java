package com.example.lwp.service;

import com.example.lwp.config.RiotConstant;
import com.example.lwp.domain.Champion;
import com.example.lwp.domain.Dataset;
import com.example.lwp.domain.Ranking;
import com.example.lwp.dto.*;
import com.example.lwp.repository.ChampionRepository;
import com.example.lwp.repository.DatasetRepository;
import com.example.lwp.repository.RankingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiService {

    @Autowired
    private final ChampionRepository championRepository;

    @Autowired
    private final RankingRepository rankingRepository;

    @Autowired
    private final DatasetRepository datasetRepository;

    public SummonerInfoDto FindSummonerInfo(String sn){

        SummonerInfoDto summonerInfoDto = new SummonerInfoDto();

        List<SummonerRankInfoDto> summonerRankInfoDtos = new ArrayList<>();

        try {
            // 첫 번째 빈 객체 초기화 및 추가
            SummonerRankInfoDto dto1 = new SummonerRankInfoDto();
            summonerRankInfoDtos.add(dto1);

            // 두 번째 빈 객체 초기화 및 추가
            SummonerRankInfoDto dto2 = new SummonerRankInfoDto();
            summonerRankInfoDtos.add(dto2);

            String riotIdandTag = sn;
            if (sn.contains("#")) {
                riotIdandTag = sn.replace("#", "/");
            }
            else {
                return null;
            }


            //User Info
            String apiUrl0 = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/"+riotIdandTag+"?api_key="+RiotConstant.API_KEY;
            RestTemplate restTemplate0 = new RestTemplate();
            ResponseEntity<RiotDto> response0= restTemplate0.getForEntity(apiUrl0, RiotDto.class);


            //User Info
            String apiUrl1 = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/"+response0.getBody().puuid+"?api_key="+RiotConstant.API_KEY;
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

            summonerInfoDto.setId((response1.getBody().getId()));
            summonerInfoDto.setSummonerName(response1.getBody().getName());
            summonerInfoDto.setProfileIcon(response1.getBody().getProfileIconId());
            summonerInfoDto.setSummonerLevel(response1.getBody().getSummonerLevel());
            summonerInfoDto.setRanks(summonerRankInfoDtos);
            return summonerInfoDto;
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    public List<MatchDto> FindMatch(String sn,int index){

        try {

            String riotIdandTag = sn;
            if (sn.contains("#")) {
                riotIdandTag = sn.replace("#", "/");
            }
            else {
                return null;
            }

            //puuid 가져오기
            String apiUrl1 = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/"+riotIdandTag+"?api_key="+RiotConstant.API_KEY;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<RiotDto> response1= restTemplate.getForEntity(apiUrl1, RiotDto.class);
            String puuid = response1.getBody().getPuuid();


            int startRange = index*10;
            int endRange = startRange+10;

            //matchid 가져오기
            String apiUrl2 = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/"+puuid+"/ids?start="+startRange+"&count="+endRange+"&api_key="+RiotConstant.API_KEY;
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


                    if (matchDto.getInfo().getGameMode().equals("CLASSIC")){
                        if (matchDto.getInfo().getQueueId()==420){
                            matchDto.getInfo().setGameMode("솔로 랭크");
                        }else if (matchDto.getInfo().getQueueId()==430){
                            matchDto.getInfo().setGameMode("일반 게임");
                        }else if (matchDto.getInfo().getQueueId()==440){
                            matchDto.getInfo().setGameMode("자유 랭크");
                        }
                    } else if (matchDto.getInfo().getGameMode().equals("ARAM")) {
                        matchDto.getInfo().setGameMode("칼바람");
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
            return matchDtoList;
        }catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    public MatchTimelineDto FindMatchTimeline(String sn){
        MatchTimelineDto matchTimelineDto = new MatchTimelineDto();

        try {
            String apiUrl = "https://asia.api.riotgames.com/lol/match/v5/matches/"+sn+"/timeline?api_key="+RiotConstant.API_KEY;
            ObjectMapper objectMapper = new ObjectMapper();

            matchTimelineDto = objectMapper.readValue(new URL(apiUrl), MatchTimelineDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchTimelineDto;
    }

    public List<MasteryDto> FindMastery(String sn){

        try {

            String riotIdandTag = sn;
            if (sn.contains("#")) {
                riotIdandTag = sn.replace("#", "/");
            }
            else {
                return null;
            }

            //puuid 가져오기
            String apiUrl1 = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/"+riotIdandTag+"?api_key="+RiotConstant.API_KEY;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<SummonerDto> response1= restTemplate.getForEntity(apiUrl1, SummonerDto.class);
            String puuid = response1.getBody().getPuuid();

            //matchid 가져오기
            String apiUrl2 = "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/"+puuid+"/top?count=10&api_key="+RiotConstant.API_KEY;
            ResponseEntity<MasteryDto[]> masteryResponse = restTemplate.getForEntity(apiUrl2, MasteryDto[].class);

            for (int i = 0;i<masteryResponse.getBody().length;i++){
                Champion c = championRepository.findChampionByChampionId(masteryResponse.getBody()[i].getChampionId());
                masteryResponse.getBody()[i].setChampionName(c.getChampionName());
                masteryResponse.getBody()[i].setChampionNameKr(c.getChampionNameKr());
            }


            return Arrays.asList(masteryResponse.getBody());

        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }


    public List<Champion> ReturnChampionList(){
        List<Champion> championList = new ArrayList<>();

        championList = championRepository.findAll();

        Collections.sort(championList, Comparator.comparing(Champion::getChampionNameKr));

        return championList;
    }

/*    public List<Ranking> ReturnRankingList(int gametype){
        List<Ranking> rankings = rankingRepository.findAllByGametypeOrderByLeaguePointsDesc(gametype);

        return rankings;
    }*/

    public List<Object[]> ReturnRankingList(int gametype){
        List<Object[]> rankings = rankingRepository.findData(gametype);

        return rankings;
    }

    public List<Champion> SearchChampionWithOptions(String inputValue, String selectedRole){
        List<Champion> championList;

        // 한글 유니코드 범위 확인
        boolean isKorean = inputValue.chars().anyMatch(ch -> ch >= 0xAC00 && ch <= 0xD7A3);

        if (isKorean) {
            championList = championRepository.findAllByChampionNameKrContainingAndPositionContaining(inputValue,selectedRole);
            if (championList.isEmpty()){
                championList = championRepository.findAllByNickNameAndPositionContaining(inputValue,selectedRole);
            }
        } else {
            championList = championRepository.findAllByChampionNameContainingAndPositionContaining(inputValue,selectedRole);
            if (championList.isEmpty()){
                championList = championRepository.findAllByInitialContainingAndPositionContaining(inputValue,selectedRole);
            }
        }

        Collections.sort(championList, Comparator.comparing(Champion::getChampionNameKr));

        return championList;
    }

    public List<Champion> ReturnChampions(){
        List<Champion> championList = championRepository.findAll();

        return championList;
    }

    public List<Dataset> ReturnDataset(String cn){
        List<Dataset> dataList = datasetRepository.findAllByChampionName(cn);

        return dataList;
    }

    public Dataset ReturnDataset2(String cn, String lane){

        Dataset dataSet = datasetRepository.findByChampionNameAndTeamPosition(cn,lane);

        if (dataSet == null){
            return new Dataset();
        }
        return dataSet;
    }
}

