package com.example.lwp;

import com.example.lwp.dto.MatchDto;
import com.example.lwp.service.ApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LwpApplicationTests {

	@Autowired
	private ApiService apiService;
	@Test
	void contextLoads() {
		List<MatchDto> a = new ArrayList<>();
		a = apiService.FindMatch("뷔빅휴칙휜");

		System.out.println(a.get(0).getInfo().getParticipants().get(0));
		System.out.println(a.get(0).getInfo().getParticipants().get(1));
		System.out.println(a.get(0).getInfo().getParticipants().get(2));
	}

}
