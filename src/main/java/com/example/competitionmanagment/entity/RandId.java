package com.example.competitionmanagment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RandId implements Serializable {

    @Column(name = "memeberNum")
    private int membernum;
    @Column(name = "competitionCode")
    private String competitoncode;




}
