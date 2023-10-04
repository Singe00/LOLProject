package com.example.lwp.repository;

import com.example.lwp.domain.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionRepository extends JpaRepository<Champion,Long> {
    Champion findChampionByChampionId(long championId);

    List<Champion> findByChampionIdIn(List<Long> championIds);
}
