package com.example.competitionmanagment.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Fish")
public class Fish {


    @Id
    private String name;
    private float averageWeight;

    @OneToMany(mappedBy = "fish")
    List<Hunting> fish;

    @ManyToOne
    @JoinColumn(name = "levelid",referencedColumnName = "code")
    private Level level;


}
