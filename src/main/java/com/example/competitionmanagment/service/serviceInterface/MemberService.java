package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.entity.Member;

public interface MemberService {

    Boolean memberExist(String identiyNumber);

    boolean checkdate(String code);

    Member addMemeber(Member member);

    Member searchMember(String name,int id);

    boolean Affectation(Member member);

}
