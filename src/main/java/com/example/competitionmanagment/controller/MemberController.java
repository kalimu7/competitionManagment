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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("member")
public class MemberController {


    @Autowired
    private MemberService memberService;

    @Autowired
    private RankingService rankingService;

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody MemberDto memberDto) {




            memberService.checkdate(memberDto.competitionCode);
            memberService.memberExist(memberDto);
            memberService.incrementNumberOfParticipant(memberDto.competitionCode);

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

    @GetMapping("/{code}/{page}")
    public ResponseEntity<?> fetchMemberByCompetition(@PathVariable String code, @PathVariable int page) {
        try {
            Page<Member> members = memberService.MemberByCompetition(code, page);
            List<MemberDto> memberDtos = new ArrayList<>();
            for (Member m : members) {
                MemberDto memberDto = MemberMapper.mm.toDto(m);
                memberDtos.add(memberDto);
            }
            int totalPages = members.getTotalPages();

            // Create a custom response object
            Map<String, Object> response = new HashMap<>();
            response.put("members", memberDtos);
            response.put("totalPages", totalPages);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


}
