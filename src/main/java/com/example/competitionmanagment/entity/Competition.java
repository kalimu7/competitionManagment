package com.example.competitionmanagment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Competition")
public class Competition {

    @Id
    private String code;
    private Date date;
    private Time startTime;
    private Time endTime;
    private int numberOfParticipants;
    private String location;
    private float amount;

    @ManyToMany(mappedBy = "competitions")
    private List<Member> members;

    @OneToMany(mappedBy = "competition")
    private List<Hunting> huntings;

}
