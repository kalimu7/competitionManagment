package com.example.competitionmanagment.dao;

import com.example.competitionmanagment.entity.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting,Integer> {


}
