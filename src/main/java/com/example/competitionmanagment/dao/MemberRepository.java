package com.example.competitionmanagment.dao;

import com.example.competitionmanagment.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<User,Integer> {

    Optional<User> findByIdentityNumber(String INumber);

    @Query("SELECT DISTINCT m FROM User m " +
            "JOIN Ranking r ON m = r.user " +
            "WHERE r.competition.code = :competitionCode")
    Page<User> findAllMembersByCompetitionCode(@Param("competitionCode") String competitionCode, Pageable pageable);

    List<User> findMemberByNameOrFamilyNameOrIdentityNumber(String name, String familyName, String identityNumber);
    Optional<User> findByNum(int id);

}
