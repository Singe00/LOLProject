package com.example.lwp;

import com.example.lwp.domain.Champion;
import com.example.lwp.dto.MasteryDto;
import com.example.lwp.dto.MatchTimelineDto;
import com.example.lwp.repository.ChampionRepository;
import com.example.lwp.service.ApiService;
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
}
