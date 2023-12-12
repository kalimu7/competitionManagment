package com.example.competitionmanagment.dao;

import com.example.competitionmanagment.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition,String> {


    Optional<Competition> findByCode(String code);

    List<Competition> findAllByDateIsAfter(LocalDate today);
    List<Competition> findAllByDateIsBefore(LocalDate today);


}
