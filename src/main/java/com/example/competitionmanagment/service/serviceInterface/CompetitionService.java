package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.entity.Member;

import java.util.List;

public interface CompetitionService {

    Competition addCompetition(Competition competition);

    List<Competition> fetchCompetition();

    List<Competition> selectCompetitionFilter(String filter);

    List<Member> displayMemebersOfCompetition(String code);


}
