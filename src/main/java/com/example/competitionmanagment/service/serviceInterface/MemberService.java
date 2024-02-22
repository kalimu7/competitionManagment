package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.dto.member.MemberAssignDto;
import com.example.competitionmanagment.dto.member.MemberDto;
import com.example.competitionmanagment.dto.ranking.RankingDto;
import com.example.competitionmanagment.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MemberService {

    Integer memberExist(MemberDto memberDto);

    boolean checkdate(String code);

    User addMemeber(User user, String code);

    List<User> searchMember(String name);

    void incrementNumberOfParticipant(String code);

    boolean Affectation(User user);

    List<User> fetchMemberByCompetition(String code);
    Page<User> MemberByCompetition(String code, int page);

    RankingDto Assign(MemberAssignDto memberAssignDto);

}
