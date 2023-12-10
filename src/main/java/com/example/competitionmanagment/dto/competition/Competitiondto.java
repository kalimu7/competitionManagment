package com.example.competitionmanagment.dto.competition;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


public class Competitiondto {

    public String code;
    public LocalDate date;
    public LocalTime startTime;
    public LocalTime endTime;
    public int numberOfParticipants;
    public String location;
    public float amount;

}
