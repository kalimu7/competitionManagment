package com.example.competitionmanagment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private List<User> users;

    @OneToMany(mappedBy = "competition",cascade = CascadeType.MERGE)
    private List<Hunting> huntings;

}
