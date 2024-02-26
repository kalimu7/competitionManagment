package com.example.competitionmanagment.dto.ranking;

import jakarta.validation.constraints.PositiveOrZero;

public class RankingDto {


    public int membernum;
    public String competitoncode;
    @PositiveOrZero(message = "Rank should be positive")
    public int rank;
    @PositiveOrZero(message = "score should be positive")
    public int score;


}
