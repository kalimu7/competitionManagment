package com.example.competitionmanagment.service;

import com.example.competitionmanagment.Mapper.RankingMapper;
import com.example.competitionmanagment.dao.CompetitionRepository;
import com.example.competitionmanagment.dao.MemberRepository;
import com.example.competitionmanagment.dao.RankingRepository;
import com.example.competitionmanagment.dto.member.MemberDto;
import com.example.competitionmanagment.dto.ranking.RankingDto;
import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.entity.Member;
import com.example.competitionmanagment.entity.RandId;
import com.example.competitionmanagment.entity.Ranking;
import com.example.competitionmanagment.service.serviceInterface.MemberService;
import com.example.competitionmanagment.util.MySpecificException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImp implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private RankingRepository rankingRepository;


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
    public Member addMemeber(Member member,String code) {
        competitionRepository.findByCode(code).orElseThrow(()->new MySpecificException("no competition with this code"));

        this.incrementNumberOfParticipant(code);
        return memberRepository.save(member);
    }



    @Override
    public List<Member> searchMember(String name) {

        return memberRepository.findMemberByNameOrFamilyNameOrIdentityNumber(name,name,name);


    }

    @Override
    public void incrementNumberOfParticipant(String code) {
        Optional<Competition> competition = competitionRepository.findByCode(code);
        if (competition.isPresent()){
            Competition competition1 = competition.get();
            competition1.setNumberOfParticipants(competition1.getNumberOfParticipants() + 1);
            competitionRepository.save(competition1);
        }
    }

    @Override
    public boolean Affectation(Member member) {
        return false;
    }
    @Override
    public Integer memberExist(MemberDto memberDto) {
        int res = 0;
        Optional<Member> member =  memberRepository.findByIdentityNumber(memberDto.identityNumber);

        if(member.isPresent()){

            RandId randId = new RandId();
            randId.setCompetitoncode(memberDto.competitionCode);
            randId.setMembernum(member.get().getNum());
            Optional<Ranking> ranking = rankingRepository.findById(randId);
            if(ranking.isEmpty()){

                RankingDto rankingDto = new RankingDto();
                rankingDto.competitoncode = memberDto.competitionCode;
                rankingDto.membernum = member.get().getNum();
                Ranking ranking1 = RankingMapper.RM.toEntity(rankingDto);
                rankingRepository.save(ranking1);
                this.incrementNumberOfParticipant(memberDto.competitionCode);
                res = 1;

            }else{
                res = 2;
            }

        }

        return res;
    }
}
