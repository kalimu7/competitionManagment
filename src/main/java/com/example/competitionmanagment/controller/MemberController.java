package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.Mapper.MemberDtoResponseMapper;
import com.example.competitionmanagment.Mapper.MemberMapper;
import com.example.competitionmanagment.Mapper.RankingMapper;
import com.example.competitionmanagment.dto.member.MemberAssignDto;
import com.example.competitionmanagment.dto.member.MemberDto;
import com.example.competitionmanagment.dto.member.MemberDtoWinnerResponse;
import com.example.competitionmanagment.dto.ranking.RankingDto;
import com.example.competitionmanagment.entity.User;
import com.example.competitionmanagment.entity.Ranking;
import com.example.competitionmanagment.service.serviceInterface.MemberService;
import com.example.competitionmanagment.service.serviceInterface.RankingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

            User user = MemberMapper.mm.toEntity(memberDto);
            user.setAccessionDate(LocalDate.now());
            User user1 = memberService.addMemeber(user,memberDto.competitionCode);
            //add an instance to the Ranking
            RankingDto rankingDto = new RankingDto();
            rankingDto.competitoncode = memberDto.competitionCode;
            rankingDto.membernum = user1.getNum();
            Ranking ranking = RankingMapper.RM.toEntity(rankingDto);
            rankingService.addRanking(ranking);
            return ResponseEntity.ok(user1);


    }

    @PostMapping("/assing")
    public ResponseEntity assign(@RequestBody MemberAssignDto memberAssignDto){


        return ResponseEntity.ok(memberService.Assign(memberAssignDto));


    }
    @PostMapping("/ranking")
    public ResponseEntity createranking(@Valid @RequestBody RankingDto rankingDto){

            Ranking ranking = RankingMapper.RM.toEntity(rankingDto);
            return ResponseEntity.ok(rankingService.addRanking(ranking));

    }

    @PostMapping("/{keyword}")
    public ResponseEntity search(@PathVariable String keyword ){
        List<User> users = memberService.searchMember(keyword);
        List<MemberDto> memberDtos = new ArrayList<>();
        for(User M : users){
            MemberDto memberDto = MemberMapper.mm.toDto(M);
            memberDtos.add(memberDto);
        }
        return ResponseEntity.ok(memberDtos);
    }

    @GetMapping("/{code}/{page}")
    public ResponseEntity<?> fetchMemberByCompetition(@PathVariable String code, @PathVariable int page) {
        try {
            Page<User> members = memberService.MemberByCompetition(code, page);
            List<MemberDto> memberDtos = new ArrayList<>();
            for (User m : members) {
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

    @GetMapping("/winners/{code}")
    public ResponseEntity fetchWinners(@PathVariable String code){
        
        List<Ranking> rankings = rankingService.FetchWinners(code);
        List<MemberDtoWinnerResponse> memberDtos = new ArrayList<>();
        for(Ranking R : rankings){
            MemberDtoWinnerResponse  memberDto = MemberDtoResponseMapper.MDRM.toDto(R.getUser());
            memberDto.score = R.getScore();
            memberDto.rank = R.getRank();
            memberDtos.add(memberDto);
        }
        return ResponseEntity.ok(memberDtos);
    }




}
