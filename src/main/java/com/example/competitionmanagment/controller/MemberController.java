package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.Mapper.MemberMapper;
import com.example.competitionmanagment.Mapper.RankingMapper;
import com.example.competitionmanagment.dto.member.MemberDto;
import com.example.competitionmanagment.dto.ranking.RankingDto;
import com.example.competitionmanagment.entity.Member;
import com.example.competitionmanagment.entity.Ranking;
import com.example.competitionmanagment.service.serviceInterface.MemberService;
import com.example.competitionmanagment.service.serviceInterface.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("member")
public class MemberController {


    @Autowired
    private MemberService memberService;

    @Autowired
    private RankingService rankingService;

    @PostMapping("/{code}")
    public ResponseEntity create(@RequestBody MemberDto memberDto, @PathVariable String code){
        try{

            if(!memberService.checkdate(memberDto.competitionCode)){
                return new ResponseEntity<>("inscription is closed",HttpStatus.BAD_REQUEST);
            }
            Member member = MemberMapper.mm.toEntity(memberDto);
            member.setAccessionDate(LocalDate.now());
            Member member1 = memberService.addMemeber(member);
            //add an instance to the Ranking
            RankingDto rankingDto = new RankingDto();
            rankingDto.competitoncode = memberDto.competitionCode;
            rankingDto.membernum = member1.getNum();
            Ranking ranking = RankingMapper.RM.toEntity(rankingDto);
            rankingService.addRanking(ranking);

            return ResponseEntity.ok(member1);

        }catch (Exception e){

            return new ResponseEntity<>("not created", HttpStatus.BAD_REQUEST);

        }
    }
    @PostMapping("/ranking")
    public ResponseEntity createranking(@RequestBody RankingDto rankingDto){
        try {

            Ranking ranking = RankingMapper.RM.toEntity(rankingDto);
            return ResponseEntity.ok(rankingService.addRanking(ranking));

        }catch (Exception e){

            return new ResponseEntity<>("something wrong",HttpStatus.BAD_REQUEST);

        }

    }

}
