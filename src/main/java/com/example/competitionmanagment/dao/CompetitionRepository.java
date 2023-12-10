package com.example.competitionmanagment.dao;

import com.example.competitionmanagment.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition,String> {


    Optional<Competition> findByCode(String code);

}
