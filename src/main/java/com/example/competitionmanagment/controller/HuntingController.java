package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.Mapper.HuntingMapper;
import com.example.competitionmanagment.dto.hunting.HuntingDto;
import com.example.competitionmanagment.entity.Hunting;
import com.example.competitionmanagment.service.serviceInterface.HuntingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hunting")
public class HuntingController {

    @Autowired
    private HuntingService huntingService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody HuntingDto huntingDto){

        try {
            Hunting hunting = HuntingMapper.HM.toEntity(huntingDto);
            return ResponseEntity.ok(huntingService.addHunting(hunting));

        }catch (Exception e){
            return new ResponseEntity<>("hunting cant be saved", HttpStatus.BAD_REQUEST);
        }

    }

}
