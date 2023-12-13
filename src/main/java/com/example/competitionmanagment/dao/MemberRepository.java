package com.example.competitionmanagment.dao;

import com.example.competitionmanagment.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

    Optional<Member> findByIdentityNumber(String INumber);



    List<Member> findMemberByNameOrFamilyNameOrIdentityNumber(String name, String familyName, String identityNumber);
    Optional<Member> findByNum(int id);

}
