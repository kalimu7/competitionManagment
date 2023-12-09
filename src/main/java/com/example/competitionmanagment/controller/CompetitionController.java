package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.Mapper.CompetitionMapper;
import com.example.competitionmanagment.dto.competition.Competitiondto;
import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.service.CompetitionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("competition")
public class CompetitionController {

    @Autowired
    private CompetitionServiceImp competitionServiceImp;

    @PostMapping("")
    public ResponseEntity create(@RequestBody Competitiondto competitiondto){
        competitiondto.code = competitiondto.location.substring(0,3) + competitiondto.startTime;
        Competition competition = CompetitionMapper.competitionmapper.toEntity(competitiondto);
        try {
            return ResponseEntity.ok(competitionServiceImp.addCompetition(competition));
        }catch (Exception e){
            return new ResponseEntity<>("not created", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public String test(){
        return "fuck you";
    }




}
