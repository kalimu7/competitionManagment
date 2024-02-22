package com.example.competitionmanagment.controller;

import com.example.competitionmanagment.dto.user.UserDto;
import com.example.competitionmanagment.dto.user.UserLogin;
import com.example.competitionmanagment.service.AuthServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {


    private final AuthServiceImp authServiceImp;

    @Autowired
    public AuthController(AuthServiceImp authServiceImp) {
        this.authServiceImp = authServiceImp;
    }




    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDto userDto){

        UserDto userDto1 =  authServiceImp.SignUp(userDto);
        return ResponseEntity.ok(userDto1);

    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLogin userLogin){

        UserLogin userLogin1 = authServiceImp.Signin(userLogin);
        return ResponseEntity.ok(userLogin1);
    }


}
