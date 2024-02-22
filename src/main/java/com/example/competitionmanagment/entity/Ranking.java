package com.example.competitionmanagment.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "competitionCode",insertable = false,updatable = false)
    private Competition competition;



    private int rank;
    private int score;
}
