package com.example.competitionmanagment.dao;

import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.entity.Fish;
import com.example.competitionmanagment.entity.Hunting;
import com.example.competitionmanagment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting,Integer> {

    Hunting findHuntingByUserAndFish(User user, Fish fish);

    List<Hunting> findAllByCompetition(Competition competition);
    @Query("SELECT DISTINCT h.user.num FROM Hunting h WHERE h.competition.code = :competitionId ")
    List<Integer> FindMemberid(@Param("competitionId") String competitionId);

}
