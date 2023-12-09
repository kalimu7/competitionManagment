package com.example.competitionmanagment.service.serviceInterface;

import java.util.List;

public interface RankingService {

   boolean calcule(String code);

   List<RankingService> affichage(String code);

}
