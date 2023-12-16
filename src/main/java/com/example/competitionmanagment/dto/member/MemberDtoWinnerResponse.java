package com.example.competitionmanagment.dto.member;

import com.example.competitionmanagment.enums.identiyDocumentType;

import java.time.LocalDate;

public class MemberDtoWinnerResponse {

    public String name;
    public String familyName;
    public LocalDate accessionDate;
    public String nationality ;
    public identiyDocumentType identity;
    public String identityNumber;

    public int score;
    public int rank;


}
