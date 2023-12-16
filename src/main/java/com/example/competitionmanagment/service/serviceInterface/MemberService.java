package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.dto.member.MemberDto;
import com.example.competitionmanagment.entity.Member;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface MemberService {

    Integer memberExist(MemberDto memberDto);

    boolean checkdate(String code);

    Member addMemeber(Member member,String code);

    List<Member> searchMember(String name);

    void incrementNumberOfParticipant(String code);

    boolean Affectation(Member member);

    List<Member> fetchMemberByCompetition(String code);
    Page<Member> MemberByCompetition(String code,int page);

}
