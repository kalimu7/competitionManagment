package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.entity.Member;

import java.util.List;

public interface CompetitionService {

    Competition addCompetition(Competition competition);

    List<Competition> fetchCompetition();

    Competition selectCompetition(String code);

    List<Member> displayMemebersOfCompetition(String code);


}
