package com.example.competitionmanagment.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Ranking {


    @EmbeddedId
    private RandId id;

    @ManyToOne
    @JoinColumn(name = "memeberNum",insertable = false,updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competitionCode",insertable = false,updatable = false)
    private Competition competition;


    @Positive(message = "Rank should be positive")
    private int rank;
    @Positive(message = "score should be positive")
    private int score;
}
