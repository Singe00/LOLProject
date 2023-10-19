package com.example.lwp.repository;

import com.example.lwp.domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    @Query("SELECT r.summonerName, r.wins, r.losses, r.tier, r.leaguePoints, r.profileIconId, " +
            "c1.championName AS championName1, c2.championName AS championName2, c3.championName AS championName3, r.gametype " +
            "FROM Ranking r " +
            "LEFT JOIN Champion c1 ON r.championId1 = c1.championId " +
            "LEFT JOIN Champion c2 ON r.championId2 = c2.championId " +
            "LEFT JOIN Champion c3 ON r.championId3 = c3.championId " +
            "WHERE r.gametype = :gametype " +
            "ORDER BY r.leaguePoints DESC")
    List<Object[]> findData(@Param("gametype") int gametype);
}
