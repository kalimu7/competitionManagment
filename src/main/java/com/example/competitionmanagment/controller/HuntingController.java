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
    public ResponseEntity create(@RequestBody @Valid HuntingDto huntingDto,BindingResult bindingResult){

            if (bindingResult.hasErrors()) {
                List<String> errors = new ArrayList<>();
                bindingResult.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        try {


            if(!fishService.checkFishWeight(huntingDto.fishname,huntingDto.weight)){
                return new ResponseEntity<>("fish weight is less the average weight",HttpStatus.BAD_REQUEST);
            }
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

    @PostMapping("/searchFish")
    public ResponseEntity searchFish(@RequestParam String fish){
        try {

            Optional<Fish> fish1 = fishService.fishSearch(fish);
            if(fish1.isPresent()){
                FishDto fishDto = FishMapper.FM.toDto(fish1.get());
                return ResponseEntity.ok(fishDto);
            }else {
                return new ResponseEntity<>("no fish with this name",HttpStatus.OK);
            }
        }catch (Exception e){
                return new ResponseEntity<>("Something wring",HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/valide")
    public ResponseEntity checkFish(@RequestParam String fish,@RequestParam float weight){
        try {
            return ResponseEntity.ok(fishService.checkFishWeight(fish,weight));
        }catch (Exception e){
            return  new ResponseEntity<>("not inserted",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/huntings")
    public ResponseEntity fetchHunting(@RequestParam String code){

        try {
            /*
            List<Ranking> rankings = new ArrayList<>();
            List<Integer> memebers = huntingService.iddd(code);
            List<Hunting> huntings = huntingService.fetchHunting(code);
            List<HuntingDtoResponse> huntingDtoResponses = new ArrayList<>();
            for(Hunting H :huntings){
                HuntingDtoResponse huntingDtoResponse = HuntingResponseMapper.HRM.toDto(H);
                huntingDtoResponse.totalScoreForRaw =  huntingDtoResponse.fishScore * huntingDtoResponse.numberOfFish;
                huntingDtoResponses.add(huntingDtoResponse);
            }
            HashMap<Ranking,Integer> hashMap = new HashMap<>();
            for(Integer id:memebers){
                String competitionCode = "";
                int totalScorePerId = 0;
                for(HuntingDtoResponse H : huntingDtoResponses){
                    if(H.membernum == id ){
                        totalScorePerId = totalScorePerId + H.totalScoreForRaw;
                        System.out.println(" id  " +  id + " h.id " + H.membernum);
                    }else{
                        System.out.println(" here must not be equal id  " +  id + " h.id " + H.membernum);
                    }
                    competitionCode = H.competitioncode;
                }
                Ranking ranking = new Ranking();
                ranking.setScore(totalScorePerId);
                RandId randId = new RandId();
                randId.setMembernum(id);
                randId.setCompetitoncode(competitionCode);
                ranking.setId(randId);




                rankings.add(ranking);

            }
            rankings.sort(Comparator.comparingInt(Ranking::getScore).reversed());
            for (int i = 0; i < rankings.size(); i++) {
                rankings.get(i).setRank(i + 1);
            } */
            List<Ranking> rankings = huntingService.calulateScore(code);


            return ResponseEntity.ok(rankings);

        }catch (Exception e){
            return new ResponseEntity<>("something went wrong",HttpStatus.BAD_REQUEST);
        }

    }


}
