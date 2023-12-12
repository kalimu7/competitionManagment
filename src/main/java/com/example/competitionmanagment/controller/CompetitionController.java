package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.Mapper.CompetitionMapper;
import com.example.competitionmanagment.dto.competition.Competitiondto;
import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.service.CompetitionServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("competition")
public class CompetitionController {

    @Autowired
    private CompetitionServiceImp competitionServiceImp;

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody Competitiondto competitiondto,BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        competitiondto.code = competitiondto.location.substring(0,3) + competitiondto.date;
        Competition competition = CompetitionMapper.competitionmapper.toEntity(competitiondto);

        try {

            return ResponseEntity.ok(competitionServiceImp.addCompetition(competition));

        }
        catch (Exception e){
            return new ResponseEntity<>("not created", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public List<Competitiondto> test(){


            List<Competition> competitions = competitionServiceImp.fetchCompetition();
            List<Competitiondto> competitiondtos = new ArrayList<>();
            for (Competition C : competitions){
            Competitiondto competitiondto = CompetitionMapper.competitionmapper.toDto(C);
            competitiondtos.add(competitiondto);
            }

            return competitiondtos;

    }
    @GetMapping("/{filter}")
    private ResponseEntity fetchCompetitionByFilter(@PathVariable String filter){

        try {
            List<Competitiondto> competitiondtos = new ArrayList<>();
            List<Competition> competitions = competitionServiceImp.selectCompetitionFilter(filter);
            for (Competition C : competitions){
                Competitiondto competitiondto = CompetitionMapper.competitionmapper.toDto(C);
                competitiondtos.add(competitiondto);
            }
            return ResponseEntity.ok(competitiondtos);
        }catch (Exception e){
            return new ResponseEntity<>("something wrong",HttpStatus.BAD_REQUEST);
        }

    }






}
