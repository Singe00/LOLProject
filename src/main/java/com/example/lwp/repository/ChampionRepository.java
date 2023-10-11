package com.example.lwp.repository;

import com.example.lwp.domain.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionRepository extends JpaRepository<Champion,Long> {
    Champion findChampionByChampionId(long championId);

    List<Champion> findByChampionIdIn(List<Long> championIds);

    List<Champion> findAllByInitialContaining(String initial);

    List<Champion> findAllByChampionNameKrContainingAndPositionContaining(String championNameKr, String position);
    List<Champion> findAllByChampionNameContainingAndPositionContaining(String championName, String position);

    List<Champion> findAllByNickNameAndPositionContaining(String championNameKr, String position);
    List<Champion> findAllByInitialContainingAndPositionContaining(String initial, String position);
}
