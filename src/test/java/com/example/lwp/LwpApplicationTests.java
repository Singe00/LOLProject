package com.example.lwp;

import com.example.lwp.dto.MatchTimelineDto;
import com.example.lwp.service.ApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LwpApplicationTests {

    @Autowired
    private ApiService apiService;

    @Test
    void test() {
        String a = "KR_6723129261";
        MatchTimelineDto aa = new MatchTimelineDto();
        aa = apiService.FindMatchTimeline(a);

        System.out.println(aa);
    }
}
