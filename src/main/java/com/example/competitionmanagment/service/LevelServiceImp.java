package com.example.competitionmanagment.service;

import com.example.competitionmanagment.dao.LevelRepository;
import com.example.competitionmanagment.entity.Level;
import com.example.competitionmanagment.service.serviceInterface.LevelService;
import com.example.competitionmanagment.util.MySpecificException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImp implements LevelService {

    @Autowired
    private LevelRepository levelRepository;


    @Override
    public Level create(Level level) {

        List<Level> levelList = levelRepository.findAll();
        for (Level L : levelList){
            if(level.getPoints() < L.getPoints()){
                throw new MySpecificException("level should be bigger than last levels");
            }else{
                return levelRepository.save(level);
            }
        }
        return null;

    }
}
