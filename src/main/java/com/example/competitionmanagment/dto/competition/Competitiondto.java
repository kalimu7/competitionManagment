package com.example.competitionmanagment.dto.competition;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


public class Competitiondto {
    public String code;
    @FutureOrPresent(message = "date shoulnt be passed")
    public LocalDate date;
    @NotNull(message = "start time is required")
    public LocalTime startTime;
    @NotNull(message = "end time is required")
    public LocalTime endTime;
    @PositiveOrZero(message = "amount shouldnt be negative")
    public int numberOfParticipants;
    @NotBlank(message = "location time is required")
    public String location;
    @PositiveOrZero(message = "amount shouldnt be negative")
    public float amount;

}
