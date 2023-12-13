package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.Mapper.MemberMapper;
import com.example.competitionmanagment.Mapper.RankingMapper;
import com.example.competitionmanagment.dto.member.MemberDto;
import com.example.competitionmanagment.dto.ranking.RankingDto;
import com.example.competitionmanagment.entity.Member;
import com.example.competitionmanagment.entity.Ranking;
import com.example.competitionmanagment.service.serviceInterface.MemberService;
import com.example.competitionmanagment.service.serviceInterface.RankingService;
import com.example.competitionmanagment.util.MySpecificException;
import com.example.competitionmanagment.util.SpecingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {


    @Autowired
    private MemberService memberService;

    @Autowired
    private RankingService rankingService;

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody MemberDto memberDto) {




            if(!memberService.checkdate(memberDto.competitionCode)){

                throw new MySpecificException(" Inscription is closed ");
                //return new ResponseEntity<>("inscription is closed",HttpStatus.BAD_REQUEST);
            }

            Integer memberExistResult = memberService.memberExist(memberDto);

            if (!memberExistResult.equals(0)) {
                String msg = "";
                if (memberExistResult.equals(1)) {
                    msg = "Member has been assigned to the competition successfully";
                } else if (memberExistResult.equals(2)) {
                    msg = "member is already registred";
                }
                throw new SpecingException(msg);
            }

            Member member = MemberMapper.mm.toEntity(memberDto);
            member.setAccessionDate(LocalDate.now());
            Member member1 = memberService.addMemeber(member,memberDto.competitionCode);
            //add an instance to the Ranking
            RankingDto rankingDto = new RankingDto();
            rankingDto.competitoncode = memberDto.competitionCode;
            rankingDto.membernum = member1.getNum();
            Ranking ranking = RankingMapper.RM.toEntity(rankingDto);
            rankingService.addRanking(ranking);
            return ResponseEntity.ok(member1);


    }
    @PostMapping("/ranking")
    public ResponseEntity createranking(@Valid @RequestBody RankingDto rankingDto){

            Ranking ranking = RankingMapper.RM.toEntity(rankingDto);
            return ResponseEntity.ok(rankingService.addRanking(ranking));

    }

    @PostMapping("/{keyword}")
    public ResponseEntity search(@PathVariable String keyword ){
        List<Member> members = memberService.searchMember(keyword);
        List<MemberDto> memberDtos = new ArrayList<>();
        for(Member M : members){
            MemberDto memberDto = MemberMapper.mm.toDto(M);
            memberDtos.add(memberDto);
        }
        return ResponseEntity.ok(memberDtos);
    }

}
