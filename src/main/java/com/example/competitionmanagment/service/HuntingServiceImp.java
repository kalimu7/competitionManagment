package com.example.competitionmanagment.service;

import com.example.competitionmanagment.dao.HuntingRepository;
import com.example.competitionmanagment.entity.Hunting;
import com.example.competitionmanagment.service.serviceInterface.HuntingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HuntingServiceImp implements HuntingService {

    @Autowired
    private HuntingRepository huntingRepository;
    @Override
    public Hunting addHunting(Hunting hunting) {
        return huntingRepository.save(hunting);
    }
}
