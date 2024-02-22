package com.example.competitionmanagment.dao;

import com.example.competitionmanagment.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoEntity,Integer> {

    Optional<UserInfoEntity> findByEmail(String email);
}
