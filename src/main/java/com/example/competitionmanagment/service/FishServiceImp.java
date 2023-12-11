package com.example.competitionmanagment.service;

import com.example.competitionmanagment.Mapper.FishMapper;
import com.example.competitionmanagment.dao.FishRepository;
import com.example.competitionmanagment.dto.fish.FishDto;
import com.example.competitionmanagment.entity.Fish;
import com.example.competitionmanagment.service.serviceInterface.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FishServiceImp implements FishService {


    @Autowired
    private FishRepository fishRepository;




    @Override
    public Optional<Fish> fishSearch(String fishname) {

        Optional<Fish> fish = fishRepository.findByName(fishname);
        if(fish.isPresent()){
            FishDto fishDto = FishMapper.FM.toDto(fish.get());
        }
        return fish;

    }

    @Override
    public boolean checkFishWeight(String fishname, float weight) {
        Optional<Fish> fish = fishRepository.findByName(fishname);
        if(fish.isPresent()){

            FishDto fishDto = FishMapper.FM.toDto(fish.get());
            if(fishDto.averageWeight > weight ){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
}
