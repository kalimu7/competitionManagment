package com.example.competitionmanagment;

import com.example.competitionmanagment.dao.*;
import com.example.competitionmanagment.entity.*;
import com.example.competitionmanagment.service.HuntingServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HuntingServiceImpTest {

    @Autowired
    private HuntingServiceImp huntingServiceImp;

    @Autowired
    private HuntingRepository huntingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private FishRepository fishRepository;

    @Autowired
    private RankingRepository rankingRepository;

    // Assuming you have some test data in your database
    private String competitionCode = "Eni2023-12-21";
    private int memberId1 = 1302;
    private int memberId2 = 1303;
    private int memberId3 = 1352;


    @BeforeEach
    void setUp() {
        // Populate test data in the database
        Optional<Competition> competition = competitionRepository.findByCode(competitionCode);

        memberRepository.findById(memberId1);

        Optional<Member> member1 = memberRepository.findById(memberId1);
        Optional<Member> member2 = memberRepository.findById(memberId2);
        Optional<Member> member3 = memberRepository.findById(memberId3);



        Optional<Fish> fishA = fishRepository.findByName("Ember");

        //score : 50
        Optional<Fish> fishB = fishRepository.findByName("Aquaflash");


        //20

        Optional<Fish> fishC = fishRepository.findByName("Cobalttail");


        fishRepository.findByName("Cobalttail");
        //10
        Hunting hunting1 = new Hunting();
        hunting1.setNumberOfFish(2);
        hunting1.setId(752);
        hunting1.setCompetition(competition.get());
        hunting1.setMember(member1.get());
        hunting1.setFish(fishA.get());
        huntingRepository.save(hunting1);

        Hunting hunting2 = new Hunting();
        hunting1.setNumberOfFish(5);
        hunting1.setId(752);
        hunting1.setCompetition(competition.get());
        hunting1.setMember(member1.get());
        hunting1.setFish(fishA.get());
        huntingRepository.save(hunting2);



        Hunting hunting3 = new Hunting();
        hunting3.setNumberOfFish(3);
        hunting3.setId(754);
        hunting3.setCompetition(competition.get());
        hunting3.setMember(member3.get());
        hunting3.setFish(fishC.get());
        huntingRepository.save(hunting3);

    }

    @Test
    void testCalculateScoreWithoutSaving() {

        List<Ranking> actualRankings = huntingServiceImp.calculateScoreWithoutSaving(competitionCode);



        assertEquals(3, actualRankings.size());

        assertEquals(1, actualRankings.get(0).getRank());
        //assertEquals(100,actualRankings.get(0).getScore());
    }
}
