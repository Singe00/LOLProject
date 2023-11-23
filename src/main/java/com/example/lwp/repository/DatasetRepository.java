package com.example.lwp.repository;

import com.example.lwp.domain.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset,Long> {
    List<Dataset> findAllByChampionName(String cn);
    Dataset findByChampionNameAndTeamPosition(String cn, String lane);
}
