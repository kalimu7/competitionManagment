package com.example.competitionmanagment.service;

import com.example.competitionmanagment.Mapper.HuntingMapper;
import com.example.competitionmanagment.Mapper.HuntingResponseMapper;
import com.example.competitionmanagment.dao.*;
import com.example.competitionmanagment.dto.hunting.HuntingDto;
import com.example.competitionmanagment.dto.hunting.HuntingDtoResponse;
import com.example.competitionmanagment.entity.*;
import com.example.competitionmanagment.service.serviceInterface.HuntingService;
import com.example.competitionmanagment.util.MySpecificException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public class HuntingServiceImp implements HuntingService {

    @Autowired
    private HuntingRepository huntingRepository;

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private FishRepository fishRepository;

    @Override
    public Hunting addHunting(Hunting hunting) {
        memberRepository.findByNum(hunting.getMember().getNum()).orElseThrow(()-> new MySpecificException("member id not found"));
        competitionRepository.findByCode(hunting.getCompetition().getCode()).orElseThrow(()->new MySpecificException("compettion not found"));
        fishRepository.findByName(hunting.getFish().getName()).orElseThrow(()->new MySpecificException("fish name is not available"));

        HuntingDto huntingDto = searchHunting(hunting.getMember().getNum(),hunting.getFish().getName());

        if(huntingDto == null){
            return huntingRepository.save(hunting);
        }else{
            hunting.setNumberOfFish(hunting.getNumberOfFish() + huntingDto.numberOfFish);
            hunting.setId(huntingDto.id);
            return huntingRepository.save(hunting);
        }
    }

    @Override
    public HuntingDto searchHunting(int num, String fishname) {
        Member member = new Member();
        member.setNum(num);
        Fish fish = new Fish();
        fish.setName(fishname);
        Hunting hunting = huntingRepository.findHuntingByMemberAndFish(member,fish);
        HuntingDto huntingDto = HuntingMapper.HM.toDto(hunting);
        return huntingDto;

    }

    @Override
    public Hunting searchHuntingByHunting(String code) {

        return null;
    }

    @Override
    public List<Hunting> fetchHunting(String code) {

        Competition competition = new Competition();
        competition.setCode(code);

        return huntingRepository.findAllByCompetition(competition);

    }

    public List<Ranking> calulateScore(String CompetitionCode){

        List<Ranking> rankings = new ArrayList<>();
        List<Integer> memebers = iddd(CompetitionCode);
        List<Hunting> huntings = fetchHunting(CompetitionCode);
        List<HuntingDtoResponse> huntingDtoResponses = new ArrayList<>();

        for(Hunting H :huntings){
            HuntingDtoResponse huntingDtoResponse = HuntingResponseMapper.HRM.toDto(H);
            huntingDtoResponse.totalScoreForRaw =  huntingDtoResponse.fishScore * huntingDtoResponse.numberOfFish;
            huntingDtoResponses.add(huntingDtoResponse);
        }

        for(Integer id:memebers){
            String competitionCode = "";
            int totalScorePerId = 0;
            for(HuntingDtoResponse H : huntingDtoResponses){
                if(H.membernum == id ){
                    totalScorePerId = totalScorePerId + H.totalScoreForRaw;
                }else{
                }
                competitionCode = H.competitioncode;
            }

            Ranking ranking = new Ranking();
            ranking.setScore(totalScorePerId);
            RandId randId = new RandId();
            randId.setMembernum(id);
            randId.setCompetitoncode(competitionCode);
            ranking.setId(randId);

            rankings.add(ranking);

        }
        rankings.sort(Comparator.comparingInt(Ranking::getScore).reversed());
        for (int i = 0; i < rankings.size(); i++) {
            rankings.get(i).setRank(i + 1);
        }

        rankingRepository.saveAll(rankings);

        return rankings;
    }

    @Override
    public List<Integer> iddd(String code) {
        return huntingRepository.FindMemberid(code);
    }


}
