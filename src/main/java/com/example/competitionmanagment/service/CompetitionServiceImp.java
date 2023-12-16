package com.example.competitionmanagment.service;

import com.example.competitionmanagment.dao.CompetitionRepository;
import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.entity.Member;
import com.example.competitionmanagment.service.serviceInterface.CompetitionService;
import com.example.competitionmanagment.util.MySpecificException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompetitionServiceImp implements CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    //todo:condition of 24 hours before subscibing//done
    @Override
    public Competition addCompetition(Competition competition) {
        String code = competition.getCode();
        if(competitionRepository.findByCode(code).isPresent()){
            throw new MySpecificException("this competition exist please verify your inputs fields ");
        }
        return competitionRepository.save(competition);
    }

    @Override
    public Page<Competition> fetchCompetition(int Page) {

        Pageable pageable = PageRequest.of(Page,6);
        Page<Competition> competitions = competitionRepository.findAll(pageable);

        return competitions;
        //return null;
        //return competitionRepository.findAll();

    }

    @Override
    public List<Competition> selectCompetitionFilter(String filter) {

        List<Competition> competitions = new ArrayList<>();
        LocalDate today = LocalDate.now();
        if(filter.equals("open")){
            competitions = competitionRepository.findAllByDateIsAfter(today);
        }else if(filter.equals("closed")) {
            competitions = competitionRepository.findAllByDateIsBefore(today);
        }
        return competitions;
    }




    @Override
    public List<Member> displayMemebersOfCompetition(String code) {
        return null;
    }
}
