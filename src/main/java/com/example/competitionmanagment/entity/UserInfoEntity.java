package com.example.competitionmanagment.entity;

import com.example.competitionmanagment.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="UserEntity")
public class UserInfoEntity {


    @Id
    @GeneratedValue
    private int num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String password;
    @Column(nullable = false, name = "EMAIL", unique = true)
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany
    private List<UserInfoEntity> userInfoEntities;


}
