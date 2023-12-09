package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.entity.Member;

public interface MemberService {

    Member addMemeber(Member member);

    Member searchMember(String name,int id);

    boolean Affectation(Member member);

}
