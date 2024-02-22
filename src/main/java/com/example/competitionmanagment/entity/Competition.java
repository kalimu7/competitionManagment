package com.example.competitionmanagment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @FutureOrPresent(message = "Event date must be in the present or future")
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfParticipants;
    private String location;
    @Positive(message = "the amout must be positive please")
    private float amount;

    @ManyToMany(mappedBy = "competitions",cascade = CascadeType.MERGE)
    private List<Member> members;

    @OneToMany(mappedBy = "competition",cascade = CascadeType.MERGE)
    private List<Hunting> huntings;

}
