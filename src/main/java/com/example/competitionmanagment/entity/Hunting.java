package com.example.competitionmanagment.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Hunting")
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int numberOfFish;

    @ManyToOne
    @JoinColumn(name = "competitionid",referencedColumnName = "code")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "memberid",referencedColumnName = "num")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fishid",referencedColumnName = "name")
    private Fish fish;

}
