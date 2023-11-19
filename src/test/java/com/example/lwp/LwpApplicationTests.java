package com.example.lwp;

import com.example.lwp.domain.Champion;
import com.example.lwp.domain.Dataset;
import com.example.lwp.dto.MasteryDto;
import com.example.lwp.repository.ChampionRepository;
import com.example.lwp.repository.DatasetRepository;
import com.example.lwp.repository.RankingRepository;
import com.example.lwp.service.ApiService;
import com.example.lwp.service.Update;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class LwpApplicationTests {

    @Autowired
    private ApiService apiService;
    @Autowired
    private Update update;
    @Autowired
    private ChampionRepository championRepository;
    @Autowired
    private RankingRepository rankingRepository;
    @Autowired
    private DatasetRepository datasetRepository;

    @Test
    void test() {
        String a = "뷔빅휴칙휜";
        List<MasteryDto>aa = apiService.FindMastery(a);

        System.out.println(aa);
    }

    @Test
    void test2() {
        List<Long> idList = Arrays.asList(1L, 2L, 3L, 4L, 5L);

        List<Champion> champList= championRepository.findByChampionIdIn(idList);

        System.out.println(":asdasdasdsadas");
        for (int i = 0;i<5;i++){
            System.out.println(champList.get(i).getChampionName());
        }

    }

    @Test
    void test3() {
        update.updateChampionRotation();

    }

    @Test
    void test4() {
        List<Champion> championList = apiService.ReturnChampionList();

        System.out.println(championList.get(0).getChampionNameKr());
        System.out.println(championList.get(1).getChampionNameKr());
        System.out.println(championList.get(2).getChampionNameKr());
    }
    @Test
    void test5() {
        List<Champion> championList = apiService.SearchChampionWithOptions("ㅌㄹ","A");

        System.out.println(championList.get(0).getChampionNameKr());
        System.out.println(championList.get(1).getChampionNameKr());
        System.out.println(championList.get(2).getChampionNameKr());
        System.out.println(championList.get(4).getChampionNameKr());
        System.out.println(championList.get(5).getChampionNameKr());
    }

    @Test
    void test6() {
        List<Champion> championList = championRepository.findAllByInitialContaining("ㅌㄹㄷㅁㅇ");

        System.out.println(championList.get(0).getChampionNameKr());
        System.out.println(championList.get(0).getInitial());
        if (championList.get(0).getInitial().equals("ㅌㄹㄷㅁㅇ")){
            System.out.println(championList.get(0).getInitial());
        }
    }


    @Test
    void test7() {
        List<Object[]> resultList = rankingRepository.findData(1);

        for (Object[] result : resultList) {
            String summonerName = (String) result[0];
            int wins = (int) result[1];
            int losses = (int) result[2];
            String tier = (String) result[3];
            int leaguePoints = (int) result[4];
            int profileIconId = (int) result[5];
            String championNameKr1 = (String) result[6];
            String championNameKr2 = (String) result[7];
            String championNameKr3 = (String) result[8];
            int gametype = (int) result[9];

            // Print the values
            System.out.println("Summoner Name: " + summonerName);
            System.out.println("Wins: " + wins);
            System.out.println("Losses: " + losses);
            System.out.println("Tier: " + tier);
            System.out.println("League Points: " + leaguePoints);
            System.out.println("Profile Icon ID: " + profileIconId);
            System.out.println("Champion 1 Name (KR): " + championNameKr1);
            System.out.println("Champion 2 Name (KR): " + championNameKr2);
            System.out.println("Champion 3 Name (KR): " + championNameKr3);
            System.out.println("Game Type: " + gametype);
            System.out.println("---------------------");

            break;
        }
    }

    @Test
    void test8() {
        List<Dataset> t = datasetRepository.findAllByChampionName("Annie");
        System.out.println(t.get(0).getChampionName());
        System.out.println(t.get(1).getTeamPosition());
        System.out.println(t.get(2).getStartItems());

    }
}
