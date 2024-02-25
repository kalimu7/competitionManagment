package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompetitionService {

    Competition addCompetition(Competition competition);

    Page<Competition> fetchCompetition(int page);

    List<Competition> selectCompetitionFilter(String filter);

    List<String> SelectCompetitionMembers(int memberNum);

    List<User> displayMemebersOfCompetition(String code);


}
