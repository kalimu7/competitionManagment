package com.example.competitionmanagment.service;

import com.example.competitionmanagment.dao.RankingRepository;
import com.example.competitionmanagment.entity.Ranking;
import com.example.competitionmanagment.service.serviceInterface.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RankingServiceImp implements RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    @Override
    public Ranking addRanking(Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    @Override
    public boolean calcule(String code) {
        return false;
    }

    @Override
    public List<RankingService> affichage(String code) {
        return null;
    }
}
