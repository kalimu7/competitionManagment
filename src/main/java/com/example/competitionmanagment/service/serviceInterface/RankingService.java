package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.entity.Ranking;

import java.util.List;

public interface RankingService {

   Ranking addRanking(Ranking ranking);
   boolean calcule(String code);

   List<RankingService> affichage(String code);

}
