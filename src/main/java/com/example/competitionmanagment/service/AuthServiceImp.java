package com.example.competitionmanagment.service;

import com.example.competitionmanagment.Config.UserInfoManagerConfig;
import com.example.competitionmanagment.Mapper.UserMapper;
import com.example.competitionmanagment.dao.UserInfoRepo;
import com.example.competitionmanagment.dto.user.UserLogin;
import com.example.competitionmanagment.dto.user.UserDto;
import com.example.competitionmanagment.entity.User;
import com.example.competitionmanagment.entity.UserInfoEntity;
import com.example.competitionmanagment.service.serviceInterface.AuthService;
import com.example.competitionmanagment.util.JWTUtils;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoManagerConfig userInfoManagerConfig;

    public UserDto SignUp(UserDto userDto){

        String hpw = passwordEncoder.encode(userDto.getPassword());

        userDto.setPassword(hpw);

        User user  = UserMapper.UMI.toEntity(userDto);

        if(user.getRole().toString().equals("Adherent")){
            LocalDate todayDate = LocalDate.now();
            user.setAccessionDate(todayDate);
        }

        User userInfo2 = userInfoRepo.save(user);
        UserDto userDto1 = UserMapper.UMI.toDto(userInfo2);
        return userDto1;

    }

    public UserLogin Signin(UserLogin requestLogin){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestLogin.getEmail(),requestLogin.getPassword()));
        var userInfoEntity = userInfoManagerConfig.loadUserByUsername(requestLogin.getEmail());
        System.out.println("the user " + userInfoEntity);
        String jwt =  jwtUtils.GenerateToken(userInfoEntity);
        Optional<User> userInfo = userInfoRepo.findByEmail(requestLogin.getEmail());
        int Id =  userInfo.get().getNum();
        var refreshToken = jwtUtils.GenerateRefreshToken(new HashMap<>(),userInfoEntity);
        UserLogin reqRes = new UserLogin();
        reqRes.setToken(jwt);
        reqRes.setRole(userInfoEntity.getAuthorities().toString());
        reqRes.setRefreshToken(refreshToken);
        reqRes.setExpirationTime("20 minutes");
        reqRes.setId(Id);

        return reqRes;

    }

    public UserLogin RefreshToken(UserLogin RefreshTokenRequest) {

        UserLogin reqRes1 = new UserLogin();
        String Email = jwtUtils.extractUsername(RefreshTokenRequest.getToken());
        var user = userInfoManagerConfig.loadUserByUsername(Email);
        if(jwtUtils.isTokenValid(RefreshTokenRequest.getToken(),user)){

            var jwt = jwtUtils.GenerateToken(user);
            reqRes1.setToken(jwt);
            reqRes1.setRefreshToken(RefreshTokenRequest.getToken());
            reqRes1.setExpirationTime("5 minutes");

        }
        return reqRes1;

    }


}
