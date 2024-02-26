package com.example.competitionmanagment.service;

import com.example.competitionmanagment.Mapper.RankingMapper;
import com.example.competitionmanagment.dao.CompetitionRepository;
import com.example.competitionmanagment.dao.MemberRepository;
import com.example.competitionmanagment.dao.RankingRepository;
import com.example.competitionmanagment.dao.UserInfoRepo;
import com.example.competitionmanagment.dto.member.MemberAssignDto;
import com.example.competitionmanagment.dto.member.MemberDto;
import com.example.competitionmanagment.dto.ranking.RankingDto;
import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.entity.User;
import com.example.competitionmanagment.entity.RandId;
import com.example.competitionmanagment.entity.Ranking;
import com.example.competitionmanagment.service.serviceInterface.MemberService;
import com.example.competitionmanagment.util.MySpecificException;
import com.example.competitionmanagment.util.SpecingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class MemberServiceImp implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private UserInfoRepo userInfoRepo;


    @Override
    public boolean checkdate(String code) {

        Optional<Competition> competition = competitionRepository.findByCode(code);
        if(competition.isPresent()){
            LocalDate LastDay = competition.get().getDate().minusDays(1);
            LocalDate currentDay = LocalDate.now();
            if(currentDay.isBefore(LastDay)){
                //can be created
            }else{
                throw new MySpecificException(" Inscription is closed ");

            }
        }else{
            throw new MySpecificException(" No Competition has this code ");
        }

        return false;
    }

    @Override
    public User addMemeber(User user, String code) {
        competitionRepository.findByCode(code).orElseThrow(()->new MySpecificException("no competition with this code"));


        return memberRepository.save(user);
    }



    @Override
    public List<User> searchMember(String name) {

        return memberRepository.findMemberByNameOrFamilyNameOrIdentityNumber(name,name,name);


    }

    @Override
    public void incrementNumberOfParticipant(String code) {
        Optional <Competition> competition =  competitionRepository.findByCode(code);
        int numberOfActualParticipant =  rankingRepository.countByCompetitionCode(code);
        if(competition.isPresent()){
            int numb = competition.get().getNumberOfParticipants();

            if(numb <= numberOfActualParticipant){
                throw new MySpecificException("this competition is full");
            }

        }
    }

    @Override
    public boolean Affectation(User user) {
        return false;
    }

    @Override
    public List<User> fetchMemberByCompetition(String code) {
        Competition competition = new Competition();
        competition.setCode(code);
        List<Ranking> rankings = rankingRepository.findAllByCompetition(competition);
        List<User> users = rankings.stream()
                .map(Ranking::getUser)
                .toList();
        return users;
    }

    @Override
    public Page<User> MemberByCompetition(String code, int page ) {

        Pageable pageable = PageRequest.of(page ,6);
        return  memberRepository.findAllMembersByCompetitionCode(code,pageable);

    }

    @Override
    public RankingDto Assign(MemberAssignDto memberAssignDto) {

        Competition competition =  competitionRepository.findByCode(memberAssignDto.getCode()).orElseThrow( ()->  new MySpecificException("no competition with this coce"));
        User user = userInfoRepo.findByIdentityNumber(memberAssignDto.getIdentity()).orElseThrow(() -> new MySpecificException("no member with this id") );

        Ranking ranking = new Ranking();
        ranking.setRank(0);
        ranking.setScore(0);
        ranking.setCompetition(competition);
        ranking.setUser(user);
        RandId randId = new RandId();
        randId.setMembernum(user.getNum());
        randId.setCompetitoncode(competition.getCode());
        ranking.setId(randId);
        Ranking ranking1 = rankingRepository.save(ranking);
        RankingDto rankingDto = RankingMapper.RM.toDto(ranking1);
        return rankingDto;


    }


    @Override
    public Integer memberExist(MemberDto memberDto) {
        int res = 0;
        Optional<User> member =  memberRepository.findByIdentityNumber(memberDto.identityNumber);

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
                res = 1;
                throw new SpecingException("Member has been assigned to the competition successfully");

            }else{
                res = 2;
                throw new SpecingException("member is already registred");

            }

        }

        return res;
    }
}
