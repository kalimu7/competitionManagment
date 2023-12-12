package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.dto.hunting.HuntingDto;
import com.example.competitionmanagment.entity.*;

import java.util.List;

public interface HuntingService {

    Hunting addHunting(Hunting hunting);
    HuntingDto searchHunting(int num, String fishname);

    Hunting searchHuntingByHunting(String competitioncode);

    List<Hunting> fetchHunting(String code);
    List<Integer> iddd(String code);

     List<Ranking> calulateScore(String competitionCode);

}
