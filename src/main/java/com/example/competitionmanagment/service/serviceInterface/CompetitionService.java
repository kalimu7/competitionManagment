package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.entity.Member;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompetitionService {

    Competition addCompetition(Competition competition);

    Page<Competition> fetchCompetition(int page);

    List<Competition> selectCompetitionFilter(String filter);


    List<Member> displayMemebersOfCompetition(String code);


}
