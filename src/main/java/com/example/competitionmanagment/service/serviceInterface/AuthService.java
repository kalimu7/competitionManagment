package com.example.competitionmanagment.service.serviceInterface;

import com.example.competitionmanagment.dto.user.UserLogin;
import com.example.competitionmanagment.dto.user.UserDto;

public interface AuthService {

    UserLogin Signin(UserLogin requestLogin);
    UserDto SignUp(UserDto userDto);
}
