package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.dto.member.MemberDto;
import com.example.competitionmanagment.entity.Member;

import java.util.HashMap;

public interface MemberService {

    Integer memberExist(MemberDto memberDto);

    boolean checkdate(String code);

    Member addMemeber(Member member);

    Member searchMember(String name,int id);

    boolean Affectation(Member member);

}
