package com.example.competitionmanagment.service;

import com.example.competitionmanagment.dao.CompetitionRepository;
import com.example.competitionmanagment.dao.MemberRepository;
import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.entity.Member;
import com.example.competitionmanagment.service.serviceInterface.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MemberServiceImp implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CompetitionRepository competitionRepository;


    @Override
    public boolean checkdate(String code) {

        Optional<Competition> competition = competitionRepository.findByCode(code);
        if(competition.isPresent()){
            LocalDate LastDay = competition.get().getDate().minusDays(1);
            LocalDate currentDay = LocalDate.now();
            if(currentDay.isBefore(LastDay)){
                return true;
            }else{
                return false;
            }
        }
        return false;

    }

    @Override
    public Member addMemeber(Member member) {

        return memberRepository.save(member);

    }



    @Override
    public Member searchMember(String name, int id) {
        return null;
    }

    @Override
    public boolean Affectation(Member member) {
        return false;
    }
    @Override
    public Boolean memberExist(String identiyNumber) {
        Optional<Member> member =  memberRepository.findByIdentityNumber(identiyNumber);

        if(member.isPresent()){
            return false;
        }
        return true;
    }
}
