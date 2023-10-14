package com.example.lwp.repository;

import com.example.lwp.domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    List<Ranking> findAllByGametypeOrderByLeaguePointsDesc(int gametype);
}
