package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.Mapper.FishMapper;
import com.example.competitionmanagment.dto.fish.FishDto;
import com.example.competitionmanagment.entity.Fish;
import com.example.competitionmanagment.service.serviceInterface.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("fish")
public class FishController {

    @Autowired
    private FishService fishService;
    @GetMapping("")
    public List<FishDto> fetch(){
        List<FishDto> fishDtos = new ArrayList<>();
        List<Fish> fish = fishService.FetchFish();
        for(Fish fish1 : fish){
           FishDto fishDto =  FishMapper.FM.toDto(fish1);
           fishDtos.add(fishDto);
        }
        return fishDtos;


    }

}
