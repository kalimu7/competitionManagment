package com.example.competitionmanagment.dao;

import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.entity.RandId;
import com.example.competitionmanagment.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RandId> {

    List<Ranking> findAllByCompetition(Competition competition);

    @Query("SELECT COUNT(r) FROM Ranking r WHERE r.competition.code = :competitionCode")
    int countByCompetitionCode(@Param("competitionCode") String competitionCode);
}
