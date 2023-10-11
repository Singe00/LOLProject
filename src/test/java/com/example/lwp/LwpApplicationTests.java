package com.example.lwp;

import com.example.lwp.domain.Champion;
import com.example.lwp.dto.MasteryDto;
import com.example.lwp.dto.MatchTimelineDto;
import com.example.lwp.repository.ChampionRepository;
import com.example.lwp.service.ApiService;
import com.example.lwp.service.Update;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
}
