package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.Mapper.CompetitionMapper;
import com.example.competitionmanagment.dto.competition.Competitiondto;
import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.service.CompetitionServiceImp;
import com.example.competitionmanagment.util.MySpecificException;
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
    public ResponseEntity create(@Valid @RequestBody Competitiondto competitiondto){





        competitiondto.code = competitiondto.location.substring(0,3) + competitiondto.date;
        Competition competition = CompetitionMapper.competitionmapper.toEntity(competitiondto);

        return ResponseEntity.ok(competitionServiceImp.addCompetition(competition));





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
    private ResponseEntity fetchCompetitionByFilter(@PathVariable String filter) {


        List<Competitiondto> competitiondtos = new ArrayList<>();
        List<Competition> competitions = competitionServiceImp.selectCompetitionFilter(filter);
        for (Competition C : competitions) {
            Competitiondto competitiondto = CompetitionMapper.competitionmapper.toDto(C);
            competitiondtos.add(competitiondto);
        }
        return ResponseEntity.ok(competitiondtos);


    }


}
