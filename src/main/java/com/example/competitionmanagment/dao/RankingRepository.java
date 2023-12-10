package com.example.competitionmanagment.dao;

import com.example.competitionmanagment.entity.RandId;
import com.example.competitionmanagment.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RandId> {


}
