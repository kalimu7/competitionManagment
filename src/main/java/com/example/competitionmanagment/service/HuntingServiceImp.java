package com.example.competitionmanagment.service;

import com.example.competitionmanagment.Mapper.HuntingMapper;
import com.example.competitionmanagment.dao.HuntingRepository;
import com.example.competitionmanagment.dto.hunting.HuntingDto;
import com.example.competitionmanagment.entity.Fish;
import com.example.competitionmanagment.entity.Hunting;
import com.example.competitionmanagment.entity.Member;
import com.example.competitionmanagment.service.serviceInterface.HuntingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HuntingServiceImp implements HuntingService {

    @Autowired
    private HuntingRepository huntingRepository;
    @Override
    public Hunting addHunting(Hunting hunting) {
        HuntingDto huntingDto = searchHunting(hunting.getMember().getNum(),hunting.getFish().getName());
        if(huntingDto == null){
            return huntingRepository.save(hunting);
        }else{
            hunting.setNumberOfFish(hunting.getNumberOfFish() + huntingDto.numberOfFish);
            hunting.setId(huntingDto.id);
            return huntingRepository.save(hunting);
        }
    }

    @Override
    public HuntingDto searchHunting(int num, String fishname) {
        Member member = new Member();
        member.setNum(num);
        Fish fish = new Fish();
        fish.setName(fishname);
        Hunting hunting = huntingRepository.findHuntingByMemberAndFish(member,fish);
        HuntingDto huntingDto = HuntingMapper.HM.toDto(hunting);
        return huntingDto;

    }

    @Override
    public Hunting searchHuntingByHunting(Hunting hunting) {
        //todo:later maybe
        return null;
    }


}
