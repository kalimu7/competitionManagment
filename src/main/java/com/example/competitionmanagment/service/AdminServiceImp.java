package com.example.competitionmanagment.service;

import com.example.competitionmanagment.Mapper.UserMapper;
import com.example.competitionmanagment.dao.UserInfoRepo;
import com.example.competitionmanagment.dto.user.UserDto;
import com.example.competitionmanagment.entity.User;
import com.example.competitionmanagment.service.serviceInterface.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private UserInfoRepo userInfoRepo;
    @Override
    public List<UserDto> fechUsers() {
        List<User> users = userInfoRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for(User u: users ){
            UserDto userDto = UserMapper.UMI.toDto(u);
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
