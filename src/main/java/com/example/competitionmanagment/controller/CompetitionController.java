package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.Mapper.CompetitionMapper;
import com.example.competitionmanagment.dto.competition.Competitiondto;
import com.example.competitionmanagment.entity.Competition;
import com.example.competitionmanagment.enums.Roles;
import com.example.competitionmanagment.service.CompetitionServiceImp;
import com.example.competitionmanagment.util.MySpecificException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PreAuthorize("hasRole('Adherent')")
    @GetMapping("/{page}")
    public ResponseEntity<?> test(@PathVariable int page) {
        try {
            Page<Competition> competitions = competitionServiceImp.fetchCompetition(page);
            List<Competitiondto> competitiondtos = new ArrayList<>();
            for (Competition C : competitions) {
                Competitiondto competitiondto = CompetitionMapper.competitionmapper.toDto(C);
                competitiondtos.add(competitiondto);
            }
            int totalPages = competitions.getTotalPages();

            Map<String, Object> response = new HashMap<>();
            response.put("competitions", competitiondtos);
            response.put("totalPages", totalPages);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


    @PreAuthorize("hasRole('Adherent')")
    @GetMapping("member/{memberNum}")
    public ResponseEntity fetchCompetitionMember(@PathVariable Integer memberNum){

        List<String> competitions = competitionServiceImp.SelectCompetitionMembers(memberNum);
        return ResponseEntity.ok(competitions);

    }

    /*@GetMapping("/{filter}")
    private ResponseEntity fetchCompetitionByFilter(@PathVariable String filter) {


        List<Competitiondto> competitiondtos = new ArrayList<>();
        List<Competition> competitions = competitionServiceImp.selectCompetitionFilter(filter);
        for (Competition C : competitions) {
            Competitiondto competitiondto = CompetitionMapper.competitionmapper.toDto(C);
            competitiondtos.add(competitiondto);
        }
        return ResponseEntity.ok(competitiondtos);


    }*/


}
