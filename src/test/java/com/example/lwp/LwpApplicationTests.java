package com.example.lwp;

import com.example.lwp.dto.MasteryDto;
import com.example.lwp.dto.MatchTimelineDto;
import com.example.lwp.service.ApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LwpApplicationTests {

    @Autowired
    private ApiService apiService;

    @Test
    void test() {
        String a = "뷔빅휴칙휜";
        List<MasteryDto>aa = apiService.FindMastery(a);

        System.out.println(aa);
    }
}
