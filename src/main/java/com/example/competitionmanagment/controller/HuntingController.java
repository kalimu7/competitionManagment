package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.Mapper.HuntingMapper;
import com.example.competitionmanagment.dto.hunting.HuntingDto;
import com.example.competitionmanagment.entity.Hunting;
import com.example.competitionmanagment.service.serviceInterface.HuntingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hunting")
public class HuntingController {

    @Autowired
    private HuntingService huntingService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody @Valid HuntingDto huntingDto){

        try {
            Hunting hunting = HuntingMapper.HM.toEntity(huntingDto);
            Hunting hunting1 = huntingService.addHunting(hunting);
            HuntingDto huntingDto1 = HuntingMapper.HM.toDto(hunting1);
            return ResponseEntity.ok(huntingDto1);

        }catch (Exception e){
            return new ResponseEntity<>("hunting cant be saved", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/search")
    public ResponseEntity search(@RequestParam int num,@RequestParam String fishname){
        try {




            return  ResponseEntity.ok(huntingService.searchHunting(num,fishname));

        }catch (Exception e){
            return new ResponseEntity<>("no hunting with this info",HttpStatus.BAD_REQUEST);
        }
    }


}
