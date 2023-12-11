package com.example.competitionmanagment.dto.competition;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


public class Competitiondto {

    public String code;
    @FutureOrPresent(message = "date shoulnt be passed")
    public LocalDate date;
    public LocalTime startTime;
    public LocalTime endTime;
    public int numberOfParticipants;
    public String location;
    @PositiveOrZero(message = "amount shouldnt be negative")
    public float amount;

}
