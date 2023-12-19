package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.Mapper.FishMapper;
import com.example.competitionmanagment.Mapper.HuntingMapper;
import com.example.competitionmanagment.Mapper.HuntingResponseMapper;
import com.example.competitionmanagment.dao.RankingRepository;
import com.example.competitionmanagment.dto.fish.FishDto;
import com.example.competitionmanagment.dto.hunting.HuntingDto;
import com.example.competitionmanagment.dto.hunting.HuntingDtoResponse;
import com.example.competitionmanagment.entity.Fish;
import com.example.competitionmanagment.entity.Hunting;
import com.example.competitionmanagment.entity.RandId;
import com.example.competitionmanagment.entity.Ranking;
import com.example.competitionmanagment.service.serviceInterface.FishService;
import com.example.competitionmanagment.service.serviceInterface.HuntingService;
import com.example.competitionmanagment.util.MySpecificException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("hunting")
public class HuntingController {

    @Autowired
    private HuntingService huntingService;

    @Autowired
    private FishService fishService;



    @PostMapping("")
    public ResponseEntity create(@RequestBody @Valid HuntingDto huntingDto){

            if(!fishService.checkFishWeight(huntingDto.fishname,huntingDto.weight)){
                throw new MySpecificException("fish weight is less the average weight");
            }
            Hunting hunting = HuntingMapper.HM.toEntity(huntingDto);
            Hunting hunting1 = huntingService.addHunting(hunting);
            HuntingDto huntingDto1 = HuntingMapper.HM.toDto(hunting1);
            return ResponseEntity.ok(huntingDto1);

    }

    @PostMapping("/search")
    public ResponseEntity search(@RequestParam int num,@RequestParam String fishname){


            return  ResponseEntity.ok(huntingService.searchHunting(num,fishname));


    }

    @PostMapping("/searchFish")
    public ResponseEntity searchFish(@RequestParam String fish){


            Optional<Fish> fish1 = fishService.fishSearch(fish);
            if(fish1.isPresent()){
                FishDto fishDto = FishMapper.FM.toDto(fish1.get());
                return ResponseEntity.ok(fishDto);
            }else {
                throw new MySpecificException("no fish with this name");
            }


    }

    @PostMapping("/valide")
    public ResponseEntity checkFish(@RequestParam String fish,@RequestParam float weight){

            return ResponseEntity.ok(fishService.checkFishWeight(fish,weight));

    }

    @GetMapping("/huntings")
    public ResponseEntity fetchHunting(@RequestParam String code){


        List<Ranking> rankings = huntingService.calculateScoreWithoutSaving(code);

        List<Ranking> rankings1 = huntingService.saveScores(rankings);

        return ResponseEntity.ok(rankings1);

    }


}
