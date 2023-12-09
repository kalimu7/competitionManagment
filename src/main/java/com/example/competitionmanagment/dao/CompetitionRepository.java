package com.example.competitionmanagment.dao;

import com.example.competitionmanagment.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition,String> {


}
