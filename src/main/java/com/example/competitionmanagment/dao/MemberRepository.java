package com.example.competitionmanagment.dao;

import com.example.competitionmanagment.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

    Optional<Member> findByIdentityNumber(String INumber);

    @Query("SELECT DISTINCT m FROM Member m " +
            "JOIN Ranking r ON m = r.member " +
            "WHERE r.competition.code = :competitionCode")
    Page<Member> findAllMembersByCompetitionCode(@Param("competitionCode") String competitionCode, Pageable pageable);

    List<Member> findMemberByNameOrFamilyNameOrIdentityNumber(String name, String familyName, String identityNumber);
    Optional<Member> findByNum(int id);

}
