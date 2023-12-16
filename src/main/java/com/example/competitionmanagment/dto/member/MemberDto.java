package com.example.competitionmanagment.dto.member;

import com.example.competitionmanagment.enums.identiyDocumentType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
public class MemberDto {

    public int num;
    @NotBlank(message = "name shouldnt be blank")
    public String name;
    @NotBlank(message = "fmailyname shouldnt be blank")
    public String familyName;
    @FutureOrPresent(message = "date shouldnt be passed ")
    public LocalDate accessionDate;
    @NotBlank(message = "nationality is required")
    public String nationality ;
    @NotNull(message = "identity  is required")
    public identiyDocumentType identity;
    @NotBlank(message = "identity number is required")
    public String identityNumber;

    @NotBlank(message = "competition code is required")
    public String competitionCode;


}
