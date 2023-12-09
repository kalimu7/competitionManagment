package com.example.competitionmanagment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class RandId implements Serializable {

    @Column(name = "memeberNum")
    private int membernum;
    @Column(name = "competitionCode")
    private String competitoncode;




}
